package com.sk.skipcart.orders.service;

import com.sk.skipcart.orders.dto.SkipOrderInDto;
import com.sk.skipcart.orders.dto.SkipOrderOutDto;

public interface IOrderService {
    public SkipOrderOutDto newOrder(SkipOrderInDto ordIn);
}
