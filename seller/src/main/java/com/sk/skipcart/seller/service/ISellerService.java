package com.sk.skipcart.seller.service;

import com.sk.skipcart.seller.dto.SellerDto;

import java.util.List;

public interface ISellerService {

    public SellerDto newSeller(SellerDto data);
    public SellerDto getSellerByCode(String code);
    public SellerDto getSellerById(int id);
    public SellerDto getSellerByAccount(String account);
    public SellerDto updateSeller(String code, SellerDto sellerDto);
    public List<SellerDto>getAll();
    public void deleteByCode(String code);
}
