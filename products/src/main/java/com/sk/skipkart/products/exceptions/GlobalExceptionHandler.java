package com.sk.skipkart.products.exceptions;

import com.sk.skipkart.products.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    private String code="";
    private String message="";
    private String path="";
    private HttpStatus status=HttpStatus.OK;

    @Autowired
    private ErrorDetails resultError;
    @Autowired
    private CommonUtils commonUtils;

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException me,
                                                       WebRequest request){
        Map<String,String>validationErrors=new HashMap<>();
        List<ObjectError> errors=new ArrayList<>();
        errors=me.getBindingResult().getAllErrors();
        errors.forEach((err)->{
            String field=((FieldError)err).getField();
            String message=err.getDefaultMessage();
            validationErrors.put(field,message);
        });
        return new ResponseEntity(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ProductException.class)
    public ResponseEntity handleProductExceptions(ProductException pe,
                                                  WebRequest request){
        switch(pe.getMessage()){
            case("PRODUCT_ALREADY_EXISTS")->{
                code=pe.getMessage();
                message="Product already exists with the given credentials";
                path=request.getDescription(false);
                status=HttpStatus.BAD_REQUEST;
            }
            case("PRODUCT_NOT_FOUND")->{
                code=pe.getMessage();
                message="Product not found for the given crdentials";
                path=request.getDescription(false);
                status=HttpStatus.NOT_FOUND;
            }
        }
        resultError=commonUtils.getError(code,message,path);
        return new ResponseEntity(resultError,status);
    }

}
