package com.sk.skipkart.products.exceptions;

public class ProductException extends RuntimeException{

    public enum ProductErrors{
        PRODUCT_NOT_FOUND,
        PRODUCT_ALREADY_EXISTS
    }

    public ProductException(ProductErrors pe){
        super(pe.name());
    }
}
