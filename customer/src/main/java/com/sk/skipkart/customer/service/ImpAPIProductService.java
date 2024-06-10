package com.sk.skipkart.customer.service;

import com.sk.skipkart.customer.dto.ProductOutDto;
import com.sk.skipkart.customer.dto.SkipCartDto;
import com.sk.skipkart.customer.dto.SkipOrderOutDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ImpAPIProductService implements IApiProductService {
    @Autowired
    private RestTemplate restTemplate;

    private String productUrl="http://localhost:8083/productprodcut/";

    private List<ProductOutDto> productList=new ArrayList<>();
    private List<SkipCartDto> cartlist= new ArrayList<>();
    private List<SkipOrderOutDto>orderList=new ArrayList<>();

    @Override
    public List<ProductOutDto> getAllProducts() {
        ProductOutDto[] products=restTemplate
                .getForEntity(productUrl+"getAll",ProductOutDto[].class)
                .getBody();
        productList= Arrays.asList(products);
        return productList;
    }

    @Override
    public List<ProductOutDto> getAllByCategory(String category) {
        ProductOutDto[] products=restTemplate
                .getForEntity(productUrl
                        +"getAllByCategory/"+category,ProductOutDto[].class)
                .getBody();
        productList= Arrays.asList(products);
        return productList;
    }
}
