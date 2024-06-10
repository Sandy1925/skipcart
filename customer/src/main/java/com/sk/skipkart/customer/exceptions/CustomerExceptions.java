package com.sk.skipkart.customer.exceptions;

public class CustomerExceptions extends RuntimeException {

    public enum CustomerErrors{
        CUSTOMER_NOT_FOUND,
        CUSTOMER_ALREADY_EXISTS,
    }

    public CustomerExceptions(CustomerErrors ce){
        super(ce.name());
    }
}
