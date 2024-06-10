package com.sk.skipcart.orders.controller;

import com.sk.skipcart.orders.dto.SkipOrderInDto;
import com.sk.skipcart.orders.dto.SkipOrderOutDto;
import com.sk.skipcart.orders.service.IOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService service;
    @Autowired
    private SkipOrderOutDto result;

    @PostMapping("/newOrder")
    public ResponseEntity newOrder(@RequestBody SkipOrderInDto ordIn){
        result=service.newOrder(ordIn);
        return new ResponseEntity(result, HttpStatus.OK);
    }
}
