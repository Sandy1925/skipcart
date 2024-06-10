package com.sk.skipcart.seller.exceptions;

public class SellerException extends RuntimeException{

    public enum SellerErrors{
        SELLER_NOT_FOUND,
        SELLER_ALREADY_EXISTS,
        MAIL_OR_ACCOUNT_ALREADY_EXISTS,

    }

    public SellerException(SellerErrors se){
        super(se.name());
    }
}
