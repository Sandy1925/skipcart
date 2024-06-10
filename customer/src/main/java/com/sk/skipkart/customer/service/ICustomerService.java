package com.sk.skipkart.customer.service;

import com.sk.skipkart.customer.dto.CustomerDto;

import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface ICustomerService {

    public CustomerDto newCustomer(CustomerDto cusDto) ;
    public CustomerDto getCustomerByCode(String cusCode);
    public CustomerDto getCustomerByEmail(String email);
    public List<CustomerDto> getAllCustomerByAccount(String account);
    public CustomerDto updateCustomer(String email,CustomerDto cusDto);
    public List<CustomerDto> getAll();

}
