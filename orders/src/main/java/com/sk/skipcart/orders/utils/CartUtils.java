package com.sk.skipcart.orders.utils;

import com.sk.skipcart.orders.dto.SkipCartDto;
import com.sk.skipcart.orders.entity.SkipCart;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CartUtils<T> {
    @Autowired
    private SkipCart result;
    @Autowired
    private SkipCartDto resultDto;
    @Autowired
    private ModelMapper mapper;

    private T conversionResult;
    private List<SkipCartDto>resultList= new ArrayList<>();

   /*
   *Description: Converting entities
   *Author: Santhosh Kumar
   *Params: data::T
   *Returns: T
   *Date Created: 05/06/2024
   *Date Modified:
    */
    public T convertingEntities(T data){
        if(data.getClass().equals(SkipCart.class)){
            conversionResult=(T)mapper.map(data,SkipCartDto.class);
        }
        if(data.getClass().equals(SkipCartDto.class)){
            conversionResult=(T)mapper.map(data,SkipCart.class);
        }
        return conversionResult;
    }

    /*
    *Description: Converting list of Cart to CartDto
    *Author: Santhosh Kumar
    *Params: List,SkipCart>
    *Returns: List<SkipCartDto
    *Date Created: 05/06/2024
    *Date Modified:
     */
    public List<SkipCartDto> listEntToDto(List<SkipCart>list){
        resultList=list
                .stream()
                .map((cart)->mapper.map(cart,SkipCartDto.class))
                .collect(Collectors.toList());
        return resultList;
    }
}
