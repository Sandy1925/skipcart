package com.sk.skipkart.customer.controller;

import com.sk.skipkart.customer.dto.CustomerDto;
import com.sk.skipkart.customer.service.ICustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private ICustomerService service;
    @Autowired
    private CustomerDto result;
    private List<CustomerDto> resultList=new ArrayList<>();

    @PostMapping("/newCustomer")
    public ResponseEntity newCustomer(@Valid @RequestBody CustomerDto cusDto){

           result=service.newCustomer(cusDto);
           return new ResponseEntity(result,HttpStatus.OK);
    }

    @GetMapping("/getByEmail/{email}")
    public ResponseEntity getByEmail(@PathVariable String email){
        result=service.getCustomerByEmail(email);
        return new ResponseEntity(result,HttpStatus.OK);
    }

    @GetMapping("/getByCode/{code}")
    public ResponseEntity getByCode(@PathVariable String code){
        result=service.getCustomerByCode(code);
        return new ResponseEntity(result,HttpStatus.OK);
    }

    @PostMapping("/updateCustomer/{code}")
    public ResponseEntity updateCustomer(@PathVariable String code,
                                         @Valid @RequestBody CustomerDto cusDto ){
        result = service.updateCustomer(code, cusDto);
        return new ResponseEntity(result,HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        resultList=service.getAll();
        return new ResponseEntity(resultList,HttpStatus.OK);
    }

    @GetMapping("/getAllByAccount/{account}")
    public ResponseEntity getAllByCustomerAccount(@PathVariable String account){
        resultList=service.getAllCustomerByAccount(account);
        return new ResponseEntity(resultList,HttpStatus.OK);
    }
}
