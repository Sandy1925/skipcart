package com.sk.skipkart.customer.exceptions;

import com.sk.skipkart.customer.utils.CommonUtils;
import org.slf4j.Logger;
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
public class GlobalExceptionHandler  {

    private String message="";
    private String code="";
    private HttpStatus status=HttpStatus.BAD_REQUEST;
    private ErrorDetails resultError=new ErrorDetails();
    @Autowired
    private CommonUtils commUtils ;
    @Autowired
    private Logger logger;


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,

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

    @ExceptionHandler(CustomerExceptions.class)
    public ResponseEntity handleExceptions(CustomerExceptions ce,
                                           WebRequest request){
            switch(ce.getMessage()){
                case ("CUSTOMER_NOT_FOUND")->{
                    code=ce.getMessage();
                    message="The customer not found";
                    status=HttpStatus.NOT_FOUND;
                }
                case ("CUSTOMER_ALREADY_EXISTS")->{
                    code=ce.getMessage();
                    message="The customer already exists";
                    status=HttpStatus.BAD_REQUEST;
                }

            }
            resultError=commUtils.getError(message,
                    request.getDescription(false),code);
            logger.error(resultError.getMessage());
            return new ResponseEntity(resultError,status);
    }

}
