package com.sk.skipkart.customer.service;

import com.sk.skipkart.customer.dto.SkipCartDto;
import com.sk.skipkart.customer.dto.SkipOrderInDto;
import com.sk.skipkart.customer.dto.SkipOrderOutDto;

import java.util.List;

public interface IApiOrderService {

    public SkipCartDto newCart(SkipCartDto cartDto);
    public List<SkipCartDto>getMyCart(String customerCode);
    public SkipOrderOutDto newOrder(SkipOrderInDto cartDto);
}
