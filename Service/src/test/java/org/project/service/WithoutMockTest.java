package org.project.service;

import org.junit.jupiter.api.*;
import org.project.service.models.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WithoutMockTest {
    private RestTemplate restTemplate = new RestTemplate();
    private String url = "http://localhost:8084/users";
    private List<User> expectedUsers = Arrays.asList(
            new User("1", "John", "lalala"),
            new User("2", "Jane", "lololo"),
            new User("3", "Mike", "123")
    );

    // запустить MyServer сначала
    @Test
    void usersServiceSuccessTestSeparateStartTheService() throws Exception {
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
}