package com.example.demo.response;

import java.util.Date;
import java.util.List;

import com.example.demo.model.Department;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResponseMessage {

    private HttpStatus httpStatus;

    private String message;

    private Date timestamp;

    @JsonInclude(Include.NON_NULL)
    private List<?> items;
    
    public ResponseMessage(HttpStatus httpStatus, String message) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.timestamp = new Date();
    }

    public ResponseMessage(HttpStatus httpStatus, String message, List<?> items) {
        this.httpStatus = httpStatus;
        this.message = message;
        this.items = items;
        this.timestamp = new Date();
    }
}
