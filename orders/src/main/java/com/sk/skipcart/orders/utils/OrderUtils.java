package com.sk.skipcart.orders.utils;

import com.sk.skipcart.orders.dto.SkipCartDto;
import com.sk.skipcart.orders.entity.SkipOrder;
import com.sk.skipcart.orders.repository.ISkipOrderRepository;
import com.sk.skipcart.orders.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class OrderUtils {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private SkipCartDto cartResult;
    @Autowired
    private ICartService cartService;
    @Autowired
    private ISkipOrderRepository orderRepo;

    /*
    *Description: Getting particular cart
    *Author: Santhosh Kumar
    *Params: cartDto:: SkipCartDto
    *Returns: SkipCartDto
    *Date Created: 05/06/2024
    *Date Modified:
     */
    public SkipCartDto getCart(SkipCartDto cartDto){
        List<SkipCartDto> list= cartService.getAllByCustomerCode(cartDto.getCustomerCode());
        for(SkipCartDto cart: list){
            if(cart.getProductCode().equals(cartDto.getProductCode())
            && cart.getQuantity()==cartDto.getQuantity()){
                cartResult=cart;
                break;
            }
        }
        return cartResult;
    }

    /*
    *Description: Getting order code
    *Author: Santhosh Kumar
    *Params: none
    *Returns: String
    *Date Created: 05/06/2024
    *Date modified:
     */
    public String getCode(){
        String result="";
        List<SkipOrder>list=orderRepo.findAll();
        if(list.size()==0){
            result="O0001";
        }else{
            long id=list.get(list.size()-1).getId();
            result="O000"+Long.toString(id+1);
        }
        return result;

    }

    /*
    *Description: Getting total of the cart
    *Author: Santhosh Kumar
    *Params: quantity::int, price ::double
    *Returns: double
    *Date Created: 05/06/2024
    *Date Modified:
     */
    public double getTotal(int quantity, double price){
        double result=(quantity*price)+(0.2*price);
        return result;

    }

}
