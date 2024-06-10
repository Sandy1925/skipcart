package com.sk.skipcart.seller.utils;

import com.sk.skipcart.seller.dto.SellerDto;
import com.sk.skipcart.seller.entity.Seller;
import com.sk.skipcart.seller.repository.*;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@NoArgsConstructor
public class SellerUtils<T> {
    private T result;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ISellerRepository sellerRepo;

    private List<Seller> resultList=new ArrayList<>();
    private List<SellerDto> resultDtoList=new ArrayList<>();

    /*
    *Description: Converting Entity to Dto classes using T ad generic
    * Author: Santhosh kumar
    * Params: data:: Seller || SellerDto
    * Returns: Seller || SellerDto
    * Date Created: 30/05/2024
    * Date modified:
     */
    public T convertingEntities(T data){
        if(data.getClass()==Seller.class){
            result=(T)mapper.map(data,SellerDto.class);
        }
        if(data.getClass()==SellerDto.class){
            result=(T)mapper.map(data,Seller.class);
        }
        return result;
    }

    /*
    *Description: Checking if the seller already exists
    *Author: Santhosh Kumar
    *Params: data:: String
    * Returns: boolean
    * Date Created: 30/05/2024
    * Date Modified:
     */
    public boolean checkSellerAlreadyExists(String data){
        Pattern pattern=Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE);
        Matcher matcher=pattern.matcher(data);
        Seller seller;
        if(matcher.matches()){
             seller=sellerRepo.findByEmail(data);
        }else{
             seller=sellerRepo.findByCode(data);
        }
        return seller != null;
    }

    /*
    *Description: Creating code for the seller with the last seller id
    * Author: Santhosh Kumar
    * Params: none
    * Returns: code::String
    * Date Created: 30/05/2024
    * Date Modified:
     */
    public String getCode(){
        resultList=sellerRepo.findAll();
        String result="";
        if(resultList.size()==0){
             result="S0001";
        }else{
            int id=resultList.get(resultList.size()-1).getId();
             result="S000"+Integer.toString(id+1);
        }

        return result;

    }

    /*
    *Description: Converting list of Seller to List of Seller Dto
    * Author: Santhosh kumar
    * Params: data::List<Seller>
    *Returns: List<SellerDto>
    * ate Created: 04/06/2024
    *Date Modified:
     */
    public List<SellerDto> convertingListEntityToDto(List<Seller> data){
        resultDtoList=data
                .stream()
                .map((seller)->mapper.map(seller,SellerDto.class))
                .collect(Collectors.toList());
        return resultDtoList;
    }

}
