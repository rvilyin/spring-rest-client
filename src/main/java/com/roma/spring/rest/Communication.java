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
        return restTemplate.exchange(URL, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {});
    }

    public String saveUser(User user, HttpHeaders headers) {
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
        return responseEntity.getBody();
    }

    public String updateUser(User user, HttpHeaders headers) {
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
        return responseEntity.getBody();
    }


    public String deleteUser(Long id, HttpHeaders headers){
        HttpEntity<User> entity = new HttpEntity<>(headers);
        String url2 = URL + "/" + id;
        ResponseEntity<String> responseEntity = restTemplate.exchange(url2, HttpMethod.DELETE, entity, String.class);
        return responseEntity.getBody();
    }

}
