package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import com.example.demo.database.DepartmentRepository;
import com.example.demo.model.Department;
import com.example.demo.response.ResponseMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


// Maybe move logic to services later
@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private DepartmentRepository departmentRepository;


    @GetMapping("/getAll")
    public ResponseEntity<ResponseMessage> getAllDepartments() {
        List<Department> departments = departmentRepository.findAll();

        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK, "OK", departments);

        return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<ResponseMessage> getDepartmentById(@PathVariable @NotNull @DecimalMin("0") Long id) {
        ResponseMessage responseMessage = new ResponseMessage();

        Optional<Department> department = departmentRepository.findById(id);
        ArrayList<Department> departments = new ArrayList<Department>();

        if (department.isPresent()) {
            departments.add(department.get());
            
            responseMessage.setHttpStatus(HttpStatus.OK);
            responseMessage.setMessage("OK");
            responseMessage.setItems(departments);
        } else {
            responseMessage.setHttpStatus(HttpStatus.NOT_FOUND);
            responseMessage.setMessage("Department ID doesn't exist.");
        }

        return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
    }


    @GetMapping("/get/name/{searchString}")
    public ResponseEntity<ResponseMessage> searchDepartments(@PathVariable @NotNull String searchString) {
        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.OK, "OK.", departmentRepository.findByNameContaining(searchString));
        
        return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
    }



    @PostMapping("/add")
    public ResponseEntity<ResponseMessage> addDepartment(@RequestBody Department department) {
        System.out.println("Here is the dept:" + department + "\n\n");
        departmentRepository.save(department);

        ResponseMessage responseMessage = new ResponseMessage(HttpStatus.CREATED, "Department added successfully.");
        return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
    }


    @PutMapping("/update")
    public ResponseEntity<ResponseMessage> updateDepartment(@RequestBody Department department) {
        ResponseMessage responseMessage = new ResponseMessage();

        if (departmentRepository.findById(department.getId()).isPresent()) {
            departmentRepository.save(department);

            responseMessage.setHttpStatus(HttpStatus.OK);
            responseMessage.setMessage("Department updated successfully.");
        } else {
            responseMessage.setHttpStatus(HttpStatus.NOT_FOUND);
            responseMessage.setMessage("Department ID doesn't exist.");
        }

        return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseMessage> deleteDepartment(@PathVariable @NotNull Long id) {
        ResponseMessage responseMessage = new ResponseMessage();

        if (departmentRepository.findById(id).isPresent()) {
            departmentRepository.deleteById(id);

            responseMessage.setHttpStatus(HttpStatus.OK);
            responseMessage.setMessage("Department deleted successfully.");
        } else {
            responseMessage.setHttpStatus(HttpStatus.NOT_FOUND);
            responseMessage.setMessage("Department ID doesn't exist.");
        }

        return new ResponseEntity<>(responseMessage, responseMessage.getHttpStatus());
    }

}
