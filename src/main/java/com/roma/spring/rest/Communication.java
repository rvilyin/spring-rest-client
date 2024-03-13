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
    private final RestTemplate restTemplate = new RestTemplate();
    private final String URL = "http://94.198.50.185:7081/api/users";

    public String getSessionId() {
        HttpEntity<String> response =  restTemplate.getForEntity("http://94.198.50.185:7081/api/users", String.class);
        List<String> cookies = response.getHeaders().get("Set-Cookie");
        String sessionId = cookies.get(0).substring(0, cookies.get(0).indexOf(';'));
        return sessionId;
    }

    public String saveUser(User user, HttpHeaders headers) {
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String>  responseEntity = restTemplate.exchange(URL, HttpMethod.POST, entity, String.class);
        return responseEntity.getBody();

    }

    public String updateUser(User user, HttpHeaders headers) {
        HttpEntity<User> entity = new HttpEntity<>(user, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(URL, HttpMethod.PUT, entity, String.class);
        return responseEntity.getBody();
    }



    public String deleteUser(Long id, HttpHeaders headers){
        HttpEntity<User> entity = new HttpEntity<>(headers);
        String url2 = URL + "/" + id.toString();
        ResponseEntity<String> responseEntity = restTemplate.exchange(url2, HttpMethod.DELETE, entity, String.class);
        return responseEntity.getBody();
    }

}
