package com.sk.skipcart.orders.controller;

import com.sk.skipcart.orders.dto.SkipCartDto;
import com.sk.skipcart.orders.service.ICartService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private SkipCartDto result;
    @Autowired
    private ICartService service;

    private List<SkipCartDto>resultList=new ArrayList<>();

    @PostMapping("/newCart")
    public ResponseEntity newCart(@RequestBody @Valid SkipCartDto cartDto){
        result=service.newCart(cartDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/gettingAllCartByCustomerCode/{code}")
    public ResponseEntity getCartByCustomerCode(@PathVariable String code){
        resultList=service.getAllByCustomerCode(code);
        return new ResponseEntity(resultList,HttpStatus.OK);
    }

    @PostMapping("/updateCart")
    public ResponseEntity updateCart(@RequestBody SkipCartDto cartDto){
        System.out.println(cartDto);
        result=service.updateCart(cartDto);
        return new ResponseEntity(result,HttpStatus.OK);
    }

    @DeleteMapping("/deleteByCustomerCode")
    public ResponseEntity deleteByCustomerCode(@RequestBody SkipCartDto cartDto){
        service.removeFromCarts(cartDto);
        return new ResponseEntity("Deleted Successfully",HttpStatus.OK);
    }


}
