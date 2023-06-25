package org.project.service.client;

import org.project.service.models.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://localhost:8085/users";
        ResponseEntity<List<User>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {}
        );
        if (response.getStatusCode().is2xxSuccessful()) {
            List<User> users = response.getBody();
            if (users != null) {
                users.stream().forEach(user -> {System.out.println(user);});
            } else {
                System.out.println("Response body is empty.");
            }
        } else {
            System.out.println("Request failed. Response Code: " + response.getStatusCodeValue());
        }
    }

}
