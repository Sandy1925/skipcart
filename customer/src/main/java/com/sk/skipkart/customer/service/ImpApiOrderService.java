package com.sk.skipkart.customer.service;

import com.sk.skipkart.customer.dto.SkipCartDto;
import com.sk.skipkart.customer.dto.SkipOrderInDto;
import com.sk.skipkart.customer.dto.SkipOrderOutDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ImpApiOrderService implements IApiOrderService {

    @Autowired
    private RestTemplate restTemplate;

    private List<SkipCartDto>cartList= new ArrayList<>();
    private SkipOrderOutDto order=new SkipOrderOutDto();
    private String cartUrl="http://localhost:8085/cart/";
    private String orderUrl="http://localhost:8085/order";

    @Override
    public SkipCartDto newCart(SkipCartDto cartDto) {
        SkipCartDto carts=restTemplate
                .postForEntity(cartUrl+"newCart",cartDto,SkipCartDto.class)
                .getBody();
        return carts;
    }

    @Override
    public List<SkipCartDto> getMyCart(String customerCode) {
        SkipCartDto[] carts=restTemplate
                .getForEntity(cartUrl+"gettingAllCartByCustomerCode/"
                        +customerCode,SkipCartDto[].class)
                .getBody();
        cartList= Arrays.asList(carts);
        return cartList;
    }

    @Override
    public SkipOrderOutDto newOrder(SkipOrderInDto cartDto) {
        order=restTemplate
                .postForEntity(orderUrl+"newOrder"
                        ,cartDto.getCart(),SkipOrderOutDto.class)
                .getBody();
        return order;
    }
}
