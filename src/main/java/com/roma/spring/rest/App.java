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

        List<String> cookies = responseEntity.getHeaders().get("Set-Cookie");
//        String sessionId = cookies.get(0).split(";")[0];
//        List<String> newCookies = new ArrayList<>();
//        newCookies.add(sessionId);

        String code = "";

        HttpHeaders headers = new HttpHeaders();
        headers.put(HttpHeaders.COOKIE, cookies);
        User user = new User("James", "Brown", (byte) 22);
        user.setId(3L);
        code += communication.saveUser(user, headers);

        user.setName("Thomas");
        user.setLastName("Shelby");
        code += communication.updateUser(user, headers);

        code += communication.deleteUser(3L, headers);
        System.out.println(code);


    }
}
