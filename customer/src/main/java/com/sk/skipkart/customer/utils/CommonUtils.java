package com.sk.skipkart.customer.utils;

import com.sk.skipkart.customer.exceptions.ErrorDetails;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;

public class CommonUtils {

    ErrorDetails error=new ErrorDetails();

    /*
    *Description: Generating Error Details Object.
    * Parameter:String::message,String::Path,String::code
    * Author:Santhosh Kumar
    * Date Created: 21/05/2024
    * Date Modified:
    * Returns: ErrorDetails
     */
    public ErrorDetails getError(String message, String path, String code){
        error.setMessage(message);
        error.setCode(code);
        error.setPath(path);
        error.setTimestamp(LocalDateTime.now());
        return error;
    }
}
