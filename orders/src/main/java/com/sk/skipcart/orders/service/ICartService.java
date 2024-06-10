package com.sk.skipcart.orders.service;

import com.sk.skipcart.orders.dto.SkipCartDto;

import java.util.List;

public interface ICartService {

    public SkipCartDto newCart(SkipCartDto cartDto);
    public List<SkipCartDto> getAllByCustomerCode(String customerCode);
    public SkipCartDto updateCart(SkipCartDto cartDto);
    public void removeFromCarts(SkipCartDto cartDto);
}
