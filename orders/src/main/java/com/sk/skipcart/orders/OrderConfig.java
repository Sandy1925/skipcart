package com.sk.skipcart.orders;

import com.sk.skipcart.orders.dto.*;
import com.sk.skipcart.orders.entity.SkipCart;
import com.sk.skipcart.orders.entity.SkipOrder;
import com.sk.skipcart.orders.exceptions.ErrorDetails;
import com.sk.skipcart.orders.utils.CartUtils;
import com.sk.skipcart.orders.utils.CommonUtils;
import com.sk.skipcart.orders.utils.OrderUtils;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class OrderConfig {

    @Bean
    public SkipCart getCart(){
        return new SkipCart();
    }

    @Bean
    public SkipOrder getOrder(){
        return new SkipOrder();
    }



    @Bean
    public SkipCartDto getCartDto(){
        return new SkipCartDto();
    }

    @Bean
    public SkipOrderDto getSkipOrderDto(){
        return new SkipOrderDto();
    }




    @Bean
    public CommonUtils getCommonUtils(){
        return new CommonUtils();
    }

    @Bean
    public CartUtils getCartUtils(){
        return new CartUtils();
    }

    @Bean
    public ModelMapper getModelMapper(){
        return new ModelMapper();
    }

    @Bean
    public Logger getLogger(){
        return LoggerFactory.getLogger(OrdersApplication.class);
    }

    @Bean
    public ErrorDetails getErrorDetails(){
        return new ErrorDetails();
    }

    @Bean
    public SkipOrderOutDto getSkipOrderOutDto(){
        return new SkipOrderOutDto();
    }

    @Bean
    public SkipOrderInDto getSkipOrderInDto(){
        return new SkipOrderInDto();
    }

    @Bean
    public CustomerDto getCustomerDto(){
        return new CustomerDto();
    }

    @Bean
    public ProductOutDto getProductOutDto(){
        return new ProductOutDto();
    }

    @Bean
    public SellerDto getSellerDto(){
        return new SellerDto();
    }

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }

    @Bean
    public OrderUtils getOrderUtils(){
        return new OrderUtils();
    }



}
