package com.roma.spring.rest;


import com.roma.spring.rest.configuration.MyConfig;
import com.roma.spring.rest.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main( String[] args ) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);

        Communication communication = context.getBean("communication", Communication.class);


        HttpHeaders headers = new HttpHeaders();
        headers.set("cookie", communication.getSessionId());

        User user = new User();
        user.setId(3L);
        user.setName("James");
        user.setLastName("Brown");
        user.setAge((byte) 22);
        String code = "";
        code += communication.saveUser(user, headers);

        user.setId(3L);
        user.setName("Thomas");
        user.setLastName("Shelby");
        code += communication.updateUser(user, headers);

        code += communication.deleteUser(3L, headers);
        System.out.println(code);



    }
}
