package com.sk.skipkart.customer;


import com.sk.skipkart.customer.dto.CustomerDto;
import com.sk.skipkart.customer.entity.Customer;
import com.sk.skipkart.customer.exceptions.ErrorDetails;
import com.sk.skipkart.customer.service.ImpCustomerService;
import com.sk.skipkart.customer.utils.CommonUtils;
import com.sk.skipkart.customer.utils.CustomerUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Base64.Encoder;

@Configuration
public class CustomerConfig {

    @Bean
    public ErrorDetails getErrorDetails(){
        return new ErrorDetails();
    }

    @Bean
    public CommonUtils getCommUtils(){
        return new CommonUtils();
    }

    @Bean
    public Customer getCustomer(){
        return new Customer();
    }

    @Bean
    public CustomerUtils getCustomerUtils(){
        return new CustomerUtils();
    }

    @Bean
    public CustomerDto getCustomerDto(){
        return new CustomerDto();
    }

    @Bean
    public ModelMapper getModel(){
        return new ModelMapper();
    }

    @Bean
    public Logger getLogger(){
        return LoggerFactory.getLogger(ImpCustomerService.class);
    }

    @Bean
    public Encoder getEncoder(){
        return Base64.getEncoder();
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }





}
