package com.sk.skipcart.orders.exceptions;

import com.sk.skipcart.orders.utils.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @Autowired
    private CommonUtils commonUtils;
    @Autowired
    private ErrorDetails resultError;

    private String code="";
    private String message="";
    private HttpStatus status=HttpStatus.OK;
    private String path="";


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentValidException(MethodArgumentNotValidException me,
                                                             WebRequest request){
        Map<String,String> validationErrors=new HashMap<>();
        List<ObjectError>errors=me.getBindingResult().getAllErrors();
        errors.forEach((err)->{
            String field=((FieldError)err).getField();
            String message=err.getDefaultMessage();
            validationErrors.put(field,message);
        });
        return new ResponseEntity(validationErrors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OrderExceptions.class)
    public ResponseEntity handleOrderExceptions(OrderExceptions oe,
                                                 WebRequest request){
        switch(oe.getMessage()){
            case("CART_NOT_FOUND")->{
                code=oe.getMessage();
                message="The cart not found for the given credentials";
                status=HttpStatus.NOT_FOUND;
                path=request.getDescription(false);
            }
            case("ORDER_NOT_FOUND")->{
                code=oe.getMessage();
                message="The Order not found for the given credentials";
                status=HttpStatus.NOT_FOUND;
                path=request.getDescription(false);
            }
            case("ORDER_FAILED")->{
                code=oe.getMessage();
                message="Order failed du to some unknown error";
                status=HttpStatus.BAD_REQUEST;
                path=request.getDescription(false);
            }
            case("TRANSACTION_NOT_FOUND")->{
                code=oe.getMessage();
                message="Transaction not found for the given credentials";
                status=HttpStatus.NOT_FOUND;
                path=request.getDescription(false);
            }
        }
        resultError=commonUtils.getError(code,message,path);
        return new ResponseEntity(resultError,status);
    }

}
