package net.basil.resttemplate.service;

import net.basil.resttemplate.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestServiceImpl implements RestService {

    private RestTemplate restTemplate;
    private Byte age = -5;
    private String getListUserUrl = "http://91.241.64.178:7081/api/users";
    private HttpHeaders httpHeaders = new HttpHeaders();

    @Autowired
    RestServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void startApp() {
            getAllUsers();
            addUser();
            changeUser();
            deleteUser();
    }

    @Override
    public ResponseEntity<String> getAllUsers() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(getListUserUrl, String.class);
        httpHeaders.set("Cookie", responseEntity.getHeaders().get("Set-Cookie").get(0));
        System.out.println("Header =" + responseEntity.getHeaders());
        System.out.println("All users = " + responseEntity.getBody());
        System.out.println("<-------------->");
        return responseEntity;
    }

    @Override
    public ResponseEntity<String> addUser() {
        HttpEntity<User> requestPost = new HttpEntity<>(new User(3L, "James", "Brown", age), httpHeaders);
        ResponseEntity<String> userResponseEntity = restTemplate
                .exchange(getListUserUrl, HttpMethod.POST, requestPost, String.class);
        System.out.println("Answer part №1 = " + userResponseEntity.getBody());
        System.out.println("<-------------->");
        return userResponseEntity;
    }

    @Override
    public ResponseEntity<String> changeUser() {
        HttpEntity<User> requestPut = new HttpEntity<>(new User(3L, "Thomas", "Shelby", age), httpHeaders);
        ResponseEntity<String> userResponseEntity = restTemplate
                .exchange(getListUserUrl, HttpMethod.PUT, requestPut, String.class);
        System.out.println("Answer part №2 = " + userResponseEntity.getBody());
        System.out.println("<-------------->");
        return userResponseEntity;
    }

    @Override
    public ResponseEntity deleteUser() {
        HttpEntity<User> requestPut = new HttpEntity<>(new User(3L, "Thomas", "Shelby", age), httpHeaders);
        ResponseEntity<String> userResponseEntity = restTemplate
                .exchange(getListUserUrl + "/3", HttpMethod.DELETE, requestPut, String.class);
        System.out.println("Answer part №3 = " + userResponseEntity.getBody());
        System.out.println("<-------------->");
        return userResponseEntity;
    }
}
