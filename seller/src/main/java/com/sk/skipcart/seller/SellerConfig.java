package com.sk.skipcart.seller;

import com.sk.skipcart.seller.dto.SellerDto;
import com.sk.skipcart.seller.entity.Seller;
import com.sk.skipcart.seller.exceptions.ErrorDetails;
import com.sk.skipcart.seller.service.ImpSellerService;
import com.sk.skipcart.seller.utils.CommonUtils;
import com.sk.skipcart.seller.utils.SellerUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Base64;

@Configuration
public class SellerConfig {
    @Bean
    public Seller getSeller(){
        getLogger().info("Seller Bean");
        return new Seller();
    }
    @Bean
    public SellerDto getSellerDto(){
        return new SellerDto();
    }
    @Bean
    public ErrorDetails getErrorDetails(){
        return new ErrorDetails();
    }
    @Bean
    public CommonUtils getCommonUtils(){
        return new CommonUtils();
    }
    @Bean
    public SellerUtils getSellerUtils(){
        return new SellerUtils();
    }
    @Bean
    public ModelMapper getMapper(){
        return new ModelMapper();
    }

    @Bean
    public Logger getLogger(){
        return LoggerFactory.getLogger(ImpSellerService.class);
    }

    @Bean
    public Base64.Encoder getEncoder(){
        return Base64.getEncoder();
    }
}
