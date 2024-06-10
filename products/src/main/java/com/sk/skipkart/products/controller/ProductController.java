package com.sk.skipkart.products.controller;

import com.sk.skipkart.products.dto.ProductDto;
import com.sk.skipkart.products.dto.ProductOutDto;
import com.sk.skipkart.products.service.IProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductOutDto result;
    @Autowired
    private IProductService service;

    private List<ProductOutDto>resultList= new ArrayList<>();

    @PostMapping("/newProduct")
    public ResponseEntity newProduct(@RequestBody @Valid ProductDto prdDto){
        result=service.newProduct(prdDto);
        return new ResponseEntity(result, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        resultList=service.getAll();
        return new ResponseEntity(resultList,HttpStatus.OK);
    }

    @GetMapping("/getByCode/{code}")
    public ResponseEntity getByCode(@PathVariable String code){
        result=service.getByCode(code);
        return new ResponseEntity(result,HttpStatus.OK);
    }

    @GetMapping("/getAllByCategory/{category}")
    public ResponseEntity getAllByCategory(@PathVariable String category){
        resultList=service.getAllByCateGory(category);
        return new ResponseEntity(resultList,HttpStatus.OK);
    }

    @GetMapping("/getAllBySellerCode/{sellerCode}")
    public ResponseEntity getAllBySellerCode(@PathVariable String sellerCode){
        resultList=service.getAllBySellerCode(sellerCode);
        return new ResponseEntity(resultList,HttpStatus.OK);
    }

    @GetMapping("/getAllByStock/{stock}")
    public ResponseEntity getAllByStock(@PathVariable int stock){
        resultList=service.getAllByStock(stock);
        return new ResponseEntity(resultList,HttpStatus.OK);
    }

    @GetMapping("/getAllByPrice/{price}")
    public ResponseEntity getAllByPrice(@PathVariable double price){
        resultList=service.getAllByPrice(price);
        return new ResponseEntity(resultList,HttpStatus.OK);
    }

    @PostMapping("/updateProduct/{code}")
    public ResponseEntity updateProduct(@PathVariable String code,
                                        @Valid @RequestBody ProductDto prdDto){
        result=service.updateProduct(code,prdDto);
        return new ResponseEntity(result,HttpStatus.OK);
    }

    @DeleteMapping("/deleteByCode/{code}")
    public ResponseEntity deleteByCode(String code){
        service.deleteByCode(code);
        return new ResponseEntity("Deleted Successfully",HttpStatus.OK);
    }
}
