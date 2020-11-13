package net.basil.resttemplate.service;

import org.springframework.http.ResponseEntity;

public interface RestService {

    void startApp();

    ResponseEntity getAllUsers();

    ResponseEntity addUser();

    ResponseEntity changeUser();

    ResponseEntity deleteUser();

}
