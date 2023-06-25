package org.project.service;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.core.WireMockConfiguration;
import com.google.gson.Gson;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.project.service.dto.UserDTO;
import org.project.service.models.User;
import org.project.service.repository.RepositoryImpl;
import org.project.service.service.BusinessLogicImpl;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ServiceImplTest {

    private static WireMockServer wireMockServer;
    private RestTemplate restTemplate = new RestTemplate();
    private String url = "http://localhost:8085/users";
    @Mock
    private RepositoryImpl repositoryMock;
    @Mock
    private BusinessLogicImpl businessLogic;

    private List<User> expectedUsers = Arrays.asList(
            new User("1", "John", "lalala"),
            new User("2", "Jane", "lololo"),
            new User("3", "Mike", "123")
    );

    private List<UserDTO> expectedUsersDTO = Arrays.asList(
            new UserDTO("1", "John", "lalala"),
            new UserDTO("2", "Jane", "lololo"),
            new UserDTO("3", "Mike", "123")
    );

    @BeforeAll
    void setUp() {
        wireMockServer = new WireMockServer(WireMockConfiguration.wireMockConfig().port(8085));
        wireMockServer.start();
        configureFor("localhost", 8085);
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void usersServiceSuccessTest() throws Exception {
        // Set up the mock response from the WireMock server
        stubFor(get(urlEqualTo("/users"))
                .willReturn(aResponse()
                        .withStatus(HttpStatus.OK.value())
                        .withHeader("Content-Type", "application/json")
                        .withBody(new Gson().toJson(expectedUsers))));

        ResponseEntity<List<User>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {}
        );

        List<User> users = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedUsers, users);
    }

    @Test
    public void testRepository() {
        when(repositoryMock.getUsers()).thenReturn(expectedUsersDTO);
        List<UserDTO> users = repositoryMock.getUsers();
        assertEquals(expectedUsersDTO, users);
    }

    @Test
    public void testBusinessLogic() {
        when(businessLogic.getUsers()).thenReturn(expectedUsers);
        List<User> users = businessLogic.getUsers();
        assertEquals(expectedUsers, users);
    }

}



