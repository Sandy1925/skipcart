package com.sk.skipcart.seller.utils;

import com.sk.skipcart.seller.exceptions.ErrorDetails;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
public class CommonUtils {

    /*
    *Description: Creating Error Details
    *Author: Santhosh Kumar
    * Params: message:String, path::String, code::String
    * Returns: ErrorDetails
    * Date Created: 30/05/2024
    * Date Modified:
     */
    public ErrorDetails getError(String message, String path, String code ){
        ErrorDetails result=new ErrorDetails();
        result.setCode(code);
        result.setPath(path);
        result.setMessage(message);
        result.setTimeStamp(LocalDateTime.now());
        return result;
    }
}
