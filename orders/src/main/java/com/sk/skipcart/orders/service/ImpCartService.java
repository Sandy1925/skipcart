package com.sk.skipcart.orders.service;

import com.sk.skipcart.orders.dto.SkipCartDto;
import com.sk.skipcart.orders.entity.SkipCart;
import com.sk.skipcart.orders.exceptions.OrderExceptions;
import com.sk.skipcart.orders.repository.ICartRepository;
import com.sk.skipcart.orders.utils.CartUtils;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import static com.sk.skipcart.orders.exceptions.OrderExceptions.OrderErrors.*;

@Service
@Transactional
public class ImpCartService implements ICartService{
    @Autowired
    private ICartRepository cartRepo;
    @Autowired
    private SkipCart result;
    @Autowired
    private SkipCartDto resultDto;
    @Autowired
    private CartUtils cartUtils;
    @Autowired
    private Logger logger;

    private List<SkipCart> resultList= new ArrayList<>();
    private List<SkipCartDto>resultDtoList=new ArrayList<>();

    /*
    *Description: Creating a new Cart
    *Author: Santhosh Kumar
    *Params: cartDto:: CartDto
    *Returns: CartDto
    *Date Created: 05/06/2024
    *Date modified:
     */
    @Override
    public SkipCartDto newCart(SkipCartDto cartDto) {
        result=(SkipCart)cartUtils.convertingEntities(cartDto);
        result=cartRepo.save(result);
        logger.info("Saved cart: "+result);
        resultDto=(SkipCartDto)cartUtils.convertingEntities(result);
        logger.info("CartDto: "+resultDto);
        return resultDto;
    }
    /*
    *Description: Getting all the customers by customer code
    *Author: Santhosh kumar
    *Params: customerCode:: String
    *Returns: List<SkipCartDto>
    *Date Created: 05/06/2024
    *Date Modified:
     */
    @Override
    public List<SkipCartDto> getAllByCustomerCode(String customerCode) {
        resultList=cartRepo.findAllByCustomerCode(customerCode);
        logger.info("List of result for code: "+customerCode+" : "+resultList);
        if(resultList.size()==0){
            logger.warn("Throwing cart not found for list is zero size");
            throw new OrderExceptions(CART_NOT_FOUND);
        }
        resultDtoList=cartUtils.listEntToDto(resultList);
        logger.info("List of dto for the list: "+resultDtoList);
        return resultDtoList;
    }
    /*
    *Description: Updating cart quantity
    *Author: Santhosh kumar
    *Params: cartDto:: SkipCartDto
    *Returns: SkipCartDto
    *Date Created: 05/06/2024
    *Date Modified:
     */
    @Override
    public SkipCartDto updateCart(SkipCartDto cartDto) {
        List<SkipCart>cartList=new ArrayList<>();
        cartList=cartRepo.findAllByCustomerCode(cartDto.getCustomerCode());
        logger.info("List of cart for the customer: "+cartDto.getCustomerCode()+" : "+cartList);
        if(cartRepo.findAllByCustomerCode(cartDto.getCustomerCode())
                .size()==0){
            logger.warn("Throwing Cart not found for list is 0 ");
            throw new OrderExceptions(CART_NOT_FOUND);
        }else{
            for(SkipCart cart:cartList){
                if(cart.getProductCode().equals(cartDto.getProductCode())
                ){
                    cart.setQuantity(cartDto.getQuantity());
                    cart=cartRepo.save(cart);
                    resultDto=(SkipCartDto)cartUtils.convertingEntities(cart);
                    logger.info("Returning Cart: "+resultDto);
                }
            }
        }

        return resultDto;
    }
    /*
    *Description: Removing Carts
    *Author: Santhosh Kumar
    *Params: cartDto:: SkipCartDto
    *Returns: SkipCartDto
    *Date Created: 05/06/2024
    *Date Modified:
     */
    @Override
    public void removeFromCarts(SkipCartDto cartDto) {
        resultList=cartRepo.findAllByCustomerCode(cartDto.getCustomerCode());
        logger.info("ResultList: "+resultList);
        if(resultList.size()==0){
            throw new OrderExceptions(CART_NOT_FOUND);
        }
        for(SkipCart cart:resultList){
            if(cart.getProductCode().equals(cartDto.getProductCode())
            && cart.getQuantity()==cartDto.getQuantity()){
                cartRepo.deleteById(cart.getId());
                break;
            }
        }


    }
}
