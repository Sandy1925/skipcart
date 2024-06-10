package com.sk.skipkart.products;

import com.sk.skipkart.products.dto.ProductDto;
import com.sk.skipkart.products.dto.ProductOutDto;
import com.sk.skipkart.products.entity.Product;
import com.sk.skipkart.products.exceptions.ErrorDetails;
import com.sk.skipkart.products.service.ImpProductService;
import com.sk.skipkart.products.utils.CommonUtils;
import com.sk.skipkart.products.utils.ProductUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.modelmapper.ModelMapper;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ProductConfig {

    @Bean
    public ErrorDetails getErrorDetails(){
        return new ErrorDetails();
    }

    @Bean
    public Product getProduct(){
        return new Product();
    }

    @Bean
    public ProductDto getProductDto(){
        return new ProductDto();
    }

    @Bean
    public ProductOutDto getProductOutDto(){
        return new ProductOutDto();
    }

    @Bean
    public CommonUtils getCommonUtils(){
        return new CommonUtils();
    }

    @Bean
    public ProductUtils getProductUtils(){
        return new ProductUtils();
    }

    @Bean
    public Logger getLogger(){
         return LoggerFactory.getLogger(ImpProductService.class);
    }

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }



}
