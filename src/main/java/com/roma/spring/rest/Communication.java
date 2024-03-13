package com.roma.spring.rest;

import com.roma.spring.rest.entity.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Component
public class Communication {
    private final RestTemplate restTemplate;
    private final String URL = "http://94.198.50.185:7081/api/users";

    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<List<User>> getAllUsers() {
        return restTemplate.exchange(URL, HttpMethod.GET, null
                        , new ParameterizedTypeReference<List<User>>() {});
    }

    public String saveUser(User user, HttpHeaders headers) {
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        Long id = user.getId();

        ResponseEntity<String> responseEntity = null;

        if (id == null) {
//            responseEntity = restTemplate.postForEntity(URL, entity, String.class);

            responseEntity = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);

            System.out.println("New user was added");
            System.out.println(responseEntity.getBody());
        } else {
            System.out.println(entity);
            responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
            System.out.println("User with ID " + id + " was updated");
            System.out.println(responseEntity.getBody());
        }

        return responseEntity.getBody();

    }


    public void deleteUser(Long id){

    }

}
