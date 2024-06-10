package com.sk.skipcart.orders.service;

import com.sk.skipcart.orders.dto.*;
import com.sk.skipcart.orders.entity.SkipOrder;
import com.sk.skipcart.orders.repository.ISkipOrderRepository;
import com.sk.skipcart.orders.utils.OrderUtils;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@Service
@Transactional
public class ImpOrderService implements IOrderService{
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private ProductOutDto prdDto;
    @Autowired
    private SellerDto sellerDto;
    @Autowired
    private ISkipOrderRepository ordRepo;
    @Autowired
    private SkipOrder order;
    @Autowired
    private SkipOrderInDto orderIn;
    @Autowired
    private SkipOrderOutDto orderOut;
    @Autowired
    private ICartService cartService;
    @Autowired
    private OrderUtils ordUtils;

    @Autowired
    private Logger logger;


    private String productUrl= "http://localhost:8083/product/";
    private String customerUrl="http://localhost:8081/customer/";
    private String sellerUrl="http://localhost:8082/seller/";

    /*
    *Description: Creating new Order
    *Author: Santhosh Kumar
    *Params: orderIn::SkipOrderInDto
    *Returns: SkipOrderOutDto
    *Date Created: 05/06/2024
    *Dae Modified:
     */
    @Override
    public SkipOrderOutDto newOrder(SkipOrderInDto ordIn) {
        ProductOutDto product=restTemplate
                .getForEntity(productUrl+"getByCode/"
                        +ordIn.getCart()
                        .getProductCode(),ProductOutDto.class)
                .getBody();
        CustomerDto customer=restTemplate
                .getForEntity(customerUrl+"getByCode/"
                        +ordIn.getCart().getCustomerCode(),CustomerDto.class)
                .getBody();
        SellerDto seller=restTemplate
                .getForEntity(sellerUrl
                        +"getByCode/"+product.getSellerCode(),SellerDto.class)
                .getBody();
        SkipOrder order= new SkipOrder();
        order.setCode(ordUtils.getCode());
        order.setOrderPlacedDate(LocalDate.now());
        order.setQuantity(ordIn.getCart().getQuantity());
        order.setCustomerCode(ordIn.getCart().getCustomerCode());
        order.setProductCode(ordIn.getCart().getProductCode());
        order.setTotal(ordUtils
                .getTotal(ordIn.getCart().getQuantity(),
                        product.getPrice()));


        order=ordRepo.save(order);
        logger.info("Saved Order: "+order);

        orderOut.setOrderPlacedDate(order.getOrderPlacedDate());
        orderOut.setTotal(order.getTotal());
        orderOut.setProductCode(order.getProductCode());
        orderOut.setCode(order.getCode());
        orderOut.setQuantity(order.getQuantity());

        orderOut.setCustomerCode(order.getCustomerCode());
        orderOut.setCustomerName(customer.getName());
        orderOut.setProductName(product.getName());
        orderOut.setSellerCode(seller.getCode());
        orderOut.setSellerName(seller.getName());
        cartService.removeFromCarts(ordIn.getCart());
        return orderOut;
    }
}
