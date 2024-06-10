package com.sk.skipkart.products.service;

import com.sk.skipkart.products.dto.ProductDto;
import com.sk.skipkart.products.dto.ProductOutDto;

import java.util.List;

public interface IProductService {

    public ProductOutDto newProduct(ProductDto prdDto);
    public List<ProductOutDto> getAll();
    public ProductOutDto getByCode(String code);
    public List<ProductOutDto> getAllByCateGory(String category);
    public List<ProductOutDto> getAllBySellerCode(String sellerCode);
    public List<ProductOutDto> getAllByStock(int stock);
    public List<ProductOutDto> getAllByPrice(double price);
    public ProductOutDto updateProduct(String code,ProductDto prdDto);
    public void deleteByCode(String code);
}
