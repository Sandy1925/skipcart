package com.sk.skipkart.products.utils;

import com.sk.skipkart.products.exceptions.ErrorDetails;

import java.time.LocalDateTime;

public class CommonUtils {

    private ErrorDetails error= new ErrorDetails();

    public ErrorDetails getError(String code, String message, String path){
        error.setCode(code);
        error.setMessage(message);
        error.setPath(path);
        error.setTimeStamp(LocalDateTime.now());
        return error;
    }
}
