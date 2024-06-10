package com.sk.skipcart.orders.exceptions;

public class OrderExceptions extends RuntimeException{

    public enum OrderErrors{
        CART_NOT_FOUND,
        ORDER_NOT_FOUND,
        ORDER_FAILED,
        TRANSACTION_NOT_FOUND
    }

    public OrderExceptions(OrderErrors oe){
        super(oe.name());
    }
}
