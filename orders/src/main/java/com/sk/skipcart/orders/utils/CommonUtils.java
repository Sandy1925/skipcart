package com.sk.skipcart.orders.utils;

import com.sk.skipcart.orders.exceptions.ErrorDetails;

import java.time.LocalDateTime;

public class CommonUtils {

    ErrorDetails error=new ErrorDetails();

    public ErrorDetails getError(String code,String message, String path){
        error.setCode(code);
        error.setMessage(message);
        error.setPath(path);
        error.setTimeStamp(LocalDateTime.now());
        return error;
    }
}
