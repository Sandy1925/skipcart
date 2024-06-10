package com.sk.skipcart.seller.controller;

import com.sk.skipcart.seller.dto.SellerDto;
import com.sk.skipcart.seller.entity.Seller;
import com.sk.skipcart.seller.service.ISellerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    private Seller result;
    @Autowired
    private SellerDto resultDto;
    @Autowired
    private ISellerService service;

    private List<SellerDto> resultList=new ArrayList<>();

    @PostMapping("/newSeller")
    public ResponseEntity newSeller(@Valid @RequestBody SellerDto data){
        resultDto=service.newSeller(data);
        return new ResponseEntity(resultDto, HttpStatus.OK);
    }

    @GetMapping("/getById/{id}")
    public ResponseEntity getById(@PathVariable int id){
        resultDto=service.getSellerById(id);
        return new ResponseEntity(resultDto,HttpStatus.OK);
    }

    @GetMapping("/getByCode/{code}")
    public ResponseEntity getByCode(@PathVariable String code){
        resultDto=service.getSellerByCode(code);
        return new ResponseEntity(resultDto,HttpStatus.OK);
    }

    @GetMapping("/getByAccount/{account}")
    public ResponseEntity getByAccount(@PathVariable String account){
        resultDto=service.getSellerByAccount(account);
        return new ResponseEntity(resultDto,HttpStatus.OK);
    }

    @PostMapping("/updateSeller/{code}")
    public ResponseEntity updateSeller(@PathVariable String code,
                                       @RequestBody @Valid SellerDto sellerDto){
        resultDto=service.updateSeller(code,sellerDto);
        return new ResponseEntity(resultDto,HttpStatus.OK);

    }

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        resultList=service.getAll();
        return new ResponseEntity(resultList,HttpStatus.OK);
    }

    @DeleteMapping("/deleteByCode/{code}")
    public ResponseEntity deleteByCode(@PathVariable String code){
        service.deleteByCode(code);
        return new ResponseEntity("Deleted Successfully",HttpStatus.OK);
    }
}
