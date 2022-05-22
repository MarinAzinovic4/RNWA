package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.example.demo.database.CultureRepository;
import com.example.demo.model.Culture;
import com.example.demo.response.ResponseMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


// Maybe move logic to services later
@RestController
@RequestMapping("/culture")
public class CultureController {
    @Autowired
    private CultureRepository CultureRepository;


    @GetMapping("/getAll")
    public ResponseEntity<ResponseMessage> getAllCultures() {
        List<Culture> Cultures = CultureRepository.findAll();

        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK, "OK", Cultures);

        return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<ResponseMessage> getCultureById(@PathVariable @NotNull @DecimalMin("0") Long id) {
        ResponseMessage responseMessage = new ResponseMessage();

        Optional<Culture> Culture = CultureRepository.findById(id);
        ArrayList<Culture> Cultures = new ArrayList<Culture>();

        if (Culture.isPresent()) {
            Cultures.add(Culture.get());
            
            responseMessage.setHttpStatus(HttpStatus.OK);
            responseMessage.setMessage("OK");
            responseMessage.setItems(Cultures);
        } else {
            responseMessage.setHttpStatus(HttpStatus.NOT_FOUND);
            responseMessage.setMessage("Culture ID doesn't exist.");
        }

        return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
    }


    @GetMapping("/get/name/{searchString}")
    public ResponseEntity<ResponseMessage> searchCultures(@PathVariable @NotNull String searchString) {
        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK, "OK.", CultureRepository.findByNameContaining(searchString));
        
        return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
    }



    @PostMapping("/add")
    public ResponseEntity<ResponseMessage> addCulture(@RequestBody Culture Culture) {
        System.out.println("Here is the dept:" + Culture + "\n\n");
        CultureRepository.save(Culture);

        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.CREATED, "Culture added successfully.");
        return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
    }


    @PutMapping("/update")
    public ResponseEntity<ResponseMessage> updateCulture(@RequestBody Culture Culture) {
        ResponseMessage responseMessage = new ResponseMessage();

        if (CultureRepository.findById(Culture.getId()).isPresent()) {
            CultureRepository.save(Culture);

            responseMessage.setHttpStatus(HttpStatus.OK);
            responseMessage.setMessage("Culture updated successfully.");
        } else {
            responseMessage.setHttpStatus(HttpStatus.NOT_FOUND);
            responseMessage.setMessage("Culture ID doesn't exist.");
        }

        return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessage> deleteCulture(@PathVariable @NotNull Long id) {
        ResponseMessage responseMessage = new ResponseMessage();

        if (CultureRepository.findById(id).isPresent()) {
            CultureRepository.deleteById(id);

            responseMessage.setHttpStatus(HttpStatus.OK);
            responseMessage.setMessage("Culture deleted successfully.");
        } else {
            responseMessage.setHttpStatus(HttpStatus.NOT_FOUND);
            responseMessage.setMessage("Culture ID doesn't exist.");
        }

        return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
    }

}
