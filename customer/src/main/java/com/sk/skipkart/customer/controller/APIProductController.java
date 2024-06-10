package com.sk.skipkart.customer.controller;

import com.sk.skipkart.customer.dto.ProductOutDto;
import com.sk.skipkart.customer.service.IApiProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/customer-product")
public class APIProductController {

    @Autowired
    private IApiProductService service;

    private List<ProductOutDto>result= new ArrayList<>();

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        result=service.getAllProducts();
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/getAllByCategory/{category}")
    public ResponseEntity getAllByCategory(@PathVariable String category){
        result=service.getAllByCategory(category);
        return new ResponseEntity(result, HttpStatus.OK);
    }

}
