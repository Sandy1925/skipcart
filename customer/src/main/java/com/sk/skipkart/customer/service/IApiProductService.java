package com.sk.skipkart.customer.service;

import com.sk.skipkart.customer.dto.ProductOutDto;

import java.util.List;

public interface IApiProductService {

    public List<ProductOutDto> getAllProducts();
    public List<ProductOutDto>getAllByCategory(String category);
}
