package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.example.demo.database.CreditCardRepository;
import com.example.demo.model.CreditCard;
import com.example.demo.response.ResponseMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


// Maybe move logic to services later
@RestController
@RequestMapping("/creditcard")
public class CreditCardController {
    @Autowired
    private CreditCardRepository CreditCardRepository;


    @GetMapping("/getAll")
    public ResponseEntity<ResponseMessage> getAllCreditCards() {
        List<CreditCard> CreditCards = CreditCardRepository.findAll();

        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK, "OK", CreditCards);

        return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<ResponseMessage> getCreditCardById(@PathVariable @NotNull @DecimalMin("0") Long id) {
        ResponseMessage responseMessage = new ResponseMessage();

        Optional<CreditCard> CreditCard = CreditCardRepository.findById(id);
        ArrayList<CreditCard> CreditCards = new ArrayList<CreditCard>();

        if (CreditCard.isPresent()) {
            CreditCards.add(CreditCard.get());
            
            responseMessage.setHttpStatus(HttpStatus.OK);
            responseMessage.setMessage("OK");
            responseMessage.setItems(CreditCards);
        } else {
            responseMessage.setHttpStatus(HttpStatus.NOT_FOUND);
            responseMessage.setMessage("CreditCard ID doesn't exist.");
        }

        return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseMessage> addCreditCard(@RequestBody CreditCard CreditCard) {
        System.out.println("Here is the dept:" + CreditCard + "\n\n");
        CreditCardRepository.save(CreditCard);

        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.CREATED, "CreditCard added successfully.");
        return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
    }


    @PutMapping("/update")
    public ResponseEntity<ResponseMessage> updateCreditCard(@RequestBody CreditCard CreditCard) {
        ResponseMessage responseMessage = new ResponseMessage();

        if (CreditCardRepository.findById(CreditCard.getId()).isPresent()) {
            CreditCardRepository.save(CreditCard);

            responseMessage.setHttpStatus(HttpStatus.OK);
            responseMessage.setMessage("CreditCard updated successfully.");
        } else {
            responseMessage.setHttpStatus(HttpStatus.NOT_FOUND);
            responseMessage.setMessage("CreditCard ID doesn't exist.");
        }

        return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessage> deleteCreditCard(@PathVariable @NotNull Long id) {
        ResponseMessage responseMessage = new ResponseMessage();

        if (CreditCardRepository.findById(id).isPresent()) {
            CreditCardRepository.deleteById(id);

            responseMessage.setHttpStatus(HttpStatus.OK);
            responseMessage.setMessage("CreditCard deleted successfully.");
        } else {
            responseMessage.setHttpStatus(HttpStatus.NOT_FOUND);
            responseMessage.setMessage("CreditCard ID doesn't exist.");
        }

        return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
    }

}
