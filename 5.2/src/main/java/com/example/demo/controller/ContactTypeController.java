package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.example.demo.database.ContactTypeRepository;
import com.example.demo.model.ContactType;
import com.example.demo.response.ResponseMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contacttype")
public class ContactTypeController {
    @Autowired
    private ContactTypeRepository ContactTypeRepository;


    @GetMapping("/getAll")
    public ResponseEntity<ResponseMessage> getAllContactTypes() {
        List<ContactType> ContactTypes = ContactTypeRepository.findAll();

        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK, "OK", ContactTypes);

        return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<ResponseMessage> getContactTypeById(@PathVariable @NotNull @DecimalMin("0") Long id) {
        ResponseMessage responseMessage = new ResponseMessage();

        Optional<ContactType> ContactType = ContactTypeRepository.findById(id);
        ArrayList<ContactType> ContactTypes = new ArrayList<ContactType>();

        if (ContactType.isPresent()) {
            ContactTypes.add(ContactType.get());
            
            responseMessage.setHttpStatus(HttpStatus.OK);
            responseMessage.setMessage("OK");
            responseMessage.setItems(ContactTypes);
        } else {
            responseMessage.setHttpStatus(HttpStatus.NOT_FOUND);
            responseMessage.setMessage("ContactType ID doesn't exist.");
        }

        return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseMessage> addContactType(@RequestBody ContactType ContactType) {
        System.out.println("Here is the dept:" + ContactType + "\n\n");
        ContactTypeRepository.save(ContactType);

        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.CREATED, "ContactType added successfully.");
        return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
    }


    @PutMapping("/update")
    public ResponseEntity<ResponseMessage> updateContactType(@RequestBody ContactType ContactType) {
        ResponseMessage responseMessage = new ResponseMessage();

        if (ContactTypeRepository.findById(ContactType.getContactTypeID()).isPresent()) {
            ContactTypeRepository.save(ContactType);

            responseMessage.setHttpStatus(HttpStatus.OK);
            responseMessage.setMessage("ContactType updated successfully.");
        } else {
            responseMessage.setHttpStatus(HttpStatus.NOT_FOUND);
            responseMessage.setMessage("ContactType ID doesn't exist.");
        }

        return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessage> deleteContactType(@PathVariable @NotNull Long id) {
        ResponseMessage responseMessage = new ResponseMessage();

        if (ContactTypeRepository.findById(id).isPresent()) {
            ContactTypeRepository.deleteById(id);

            responseMessage.setHttpStatus(HttpStatus.OK);
            responseMessage.setMessage("ContactType deleted successfully.");
        } else {
            responseMessage.setHttpStatus(HttpStatus.NOT_FOUND);
            responseMessage.setMessage("ContactType ID doesn't exist.");
        }

        return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
    }

}
