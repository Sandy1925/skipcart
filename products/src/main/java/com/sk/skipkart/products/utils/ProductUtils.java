package com.sk.skipkart.products.utils;

import com.sk.skipkart.products.dto.ProductDto;
import com.sk.skipkart.products.dto.ProductOutDto;
import com.sk.skipkart.products.dto.SellerDto;
import com.sk.skipkart.products.entity.Product;
import com.sk.skipkart.products.repository.IProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductUtils<T> {

    @Autowired
    private Product result;
    @Autowired
    private ProductDto prdDto;
    @Autowired
    private ProductOutDto resultDto;
    @Autowired
    private IProductRepository prdRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private RestTemplate restTemplate;

    private T conversionResult;
    private String sellerUrl="http://localhost:8082/seller/";


    private List<ProductOutDto> resultDtoList=new ArrayList<>();

    /*
    *Description: Checking if the product already exists
    *Author: Santhosh Kumar
    *Params: code::String
    *Return: Boolean
    * Date Created: 04/06/2024
    * Date Modified:
     */
    public boolean checkAlreadyExists(String code){
        result=prdRepo.findByCode(code);
        return result!=null;
    }

    /*
    *Description: Converting entities to respective dtos and vice versa
    *Author: Santhosh kumar
    *Params: data::T
    *Returns: T
    *Date Created: 04/06/2024
    *Date Modified:
     */
    public T convertingEntities(T data){
        if(data.getClass().equals(Product.class)){
            conversionResult=(T)modelMapper.map(data,ProductOutDto.class);
        }
        if(data.getClass().equals(ProductDto.class)){
            conversionResult=(T)modelMapper.map(data,Product.class);
        }
        return conversionResult;
    }

    /*
    *Description: Generating code for the Product
    *Author: Santhosh Kumar
    *Params: none
    *Return String
    *Date Created: 04/06/2024
    *Date modified:
     */
    public String getCode(){
        String result="";
        List<Product> list= prdRepo.findAll();
        if(list.size()==0){
            result="P0001";
        }else{
            long id=list.get(list.size()-1).getId();
            result="P000"+Long.toString(id+1);
        }
        return result;
    }

    /*
    *Description: Getting seller for the product
    *Author: Santhosh Kumar
    *Params: sellerCode:: String
    *Returns: String
    *Date CreateD: 04/06/2024
    *Date Modified:
     */
    public String getSellerName(String sellerCode){
        SellerDto seller=restTemplate
                .getForEntity(sellerUrl+"getByCode/"
                        +sellerCode,SellerDto.class)
                .getBody();
        return seller.getName();
    }


    /*
    *Description: Converting list of products to list of ProductOutDto
    *Author: Santhosh kumar
    *Params: List,Product>
    *Returns: List<ProductOutDto
    *Date Created; 04/06/2024
    *Date modified:
     */
    public List<ProductOutDto> listEntityToDto(List<Product> list){
        resultDtoList=list
                .stream()
                .map((prod)->
                    modelMapper.map(prod, ProductOutDto.class))
                .collect(Collectors.toList());

        return resultDtoList;
    }


}
