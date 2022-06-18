package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.response.ResponseMessage;


@RestController
@RequestMapping("/auth")
public class LoginController {
    @GetMapping("/login")
    public ResponseEntity<ResponseMessage> getAllDepartments() {

        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK, "OK");

        return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
    }
}
