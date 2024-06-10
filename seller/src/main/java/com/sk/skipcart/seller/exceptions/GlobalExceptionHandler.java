package com.sk.skipcart.seller.exceptions;

import com.sk.skipcart.seller.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private ErrorDetails resultError;
    @Autowired
    private CommonUtils commUtils;

    private HttpStatus status=HttpStatus.OK;
    private String code="";
    private String path="";
    private String message="";

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,

                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        Map<String,String> validationErrors=new HashMap<>();
        List<ObjectError> errorList=ex.getBindingResult().getAllErrors();
        errorList.forEach((error)->{
            String fieldName=((FieldError) error).getField();
            String message=error.getDefaultMessage();
            validationErrors.put(fieldName,message);
            resultError=commUtils.getError(message,request.getDescription(false),"INVALID_INPUT");
        });


        return new ResponseEntity(resultError,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(SellerException.class)
    public ResponseEntity handleExceptions(SellerException se,WebRequest request){
        switch(se.getMessage()){
            case ("SELLER_NOT_FOUND")->{
                status=HttpStatus.NOT_FOUND;
                code=se.getMessage();
                path=request.getDescription(false);
                message="The seller does not exist with given credentials";
            }
            case ("SELLER_ALREADY_EXISTS")->{
                status=HttpStatus.BAD_REQUEST;
                code=se.getMessage();
                path=request.getDescription(false);
                message="The seller already exists with given credentials";
            }
            case ("MAIL_OR_ACCOUNT_ALREADY_EXISTS")->{
                status=HttpStatus.BAD_REQUEST;
                code=se.getMessage();
                path=request.getDescription(false);
                message="Can't change the email or account with existing one";
            }
        }
        resultError=commUtils.getError(message,path,code);
        return new ResponseEntity(resultError,status);
    }
}
