package com.roma.spring.rest;


import com.roma.spring.rest.configuration.MyConfig;
import com.roma.spring.rest.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main( String[] args ) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = context.getBean("communication", Communication.class);

        ResponseEntity<List<User>> responseEntity = communication.getAllUsers();

        System.out.println(responseEntity.getHeaders());
        List<String> cookies = responseEntity.getHeaders().get("Set-Cookie");
        System.out.println(cookies);
        String sessionId = cookies.get(0).split(";")[0];
        System.out.println(sessionId);
        List<String> newCookies = new ArrayList<>();
        newCookies.add(sessionId);
        System.out.println(newCookies);

        String code = "";

        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.COOKIE, newCookies);
        User user = new User("James", "Brown", (byte) 22);
        code += communication.saveUser(user, headers);

        user.setId(3L);
        user.setName("Thomas");
        user.setLastName("Shelby");
        code += communication.saveUser(user, headers);







    }
}
