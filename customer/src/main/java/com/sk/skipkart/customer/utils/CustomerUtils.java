package com.sk.skipkart.customer.utils;

import com.sk.skipkart.customer.dto.CustomerDto;
import com.sk.skipkart.customer.entity.Customer;
import com.sk.skipkart.customer.repository.ICustomerRepository;
import org.apache.logging.log4j.message.SimpleMessage;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.util.List;
import javax.crypto.Cipher;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CustomerUtils<T> {

    private T result;
    @Autowired
    private ModelMapper mapper;
    @Autowired
    private ICustomerRepository cusRepo;
    @Autowired
    private JavaMailSender emailSender;
    @Autowired
    private Logger logger;

    private List<Customer> resultList=new ArrayList<>();
    private List<CustomerDto>resultDtoList=new ArrayList<>();
    private Cipher cipher;
    /*
    *Description: Converting Dto to Entity and Vice versa,
    * we use Generic Class T to handle both the converting logics
    * Parameter: T it could be either Customer or CustomerDto
    * Author: Santhosh Kumar
    * Date Created: 21/05/2024
    * Date Modified:
    * Returns: T Customer or CustomerDto
     */
    public T  convertingEntities(T data){
        if(data.getClass()== Customer.class) {
            result =  (T)mapper.map(data, CustomerDto.class);
        }else if(data.getClass()==CustomerDto.class){
            result= (T)mapper.map(data,Customer.class);
        }
        return  result;
    }

    /*
    *Description:Checking if the customer already exists to store new customer
    *Parameter:String:: email or code
    * Author: Santhosh Kumar
    * Date Created: 21/05/2024
    * Date Modified:
    * Returns: Boolean
     */
    public boolean checkCustomerAlreadyExists(String data){
        Pattern pattern=Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",Pattern.CASE_INSENSITIVE);
        Matcher matcher=pattern.matcher(data);
        if(matcher.matches()){
            Customer result=cusRepo.findByEmail(data);
        }else{
            Customer result=cusRepo.findByCode(data);
        }

        return result != null;
    }

    public String getCustomerCode(){
        String result="";
        List<Customer> resultList=cusRepo.findAll();
        if(resultList.size()==0){
            result="C0001";
        }else{
            Customer cus=resultList.get(resultList.size()-1);
            result="C000"+Integer.toString(cus.getId()+1);
        }
        return result;
    }

    /*
    *Sending email with java mail sender
    * Parameter: String::To email id
    * Author: Santhosh Kumar
    * Date Created: 22/05/2024
    * Date Modified:
    * returns : null
     */

    public void sendEmail(String to){
        SimpleMailMessage message= new SimpleMailMessage();
       message.setFrom("santhoshsk25420@gmail.com");
       message.setTo("santhosh25420@gmail.com");
       message.setSubject("Welcome to Skipcart");
       message.setText("Happy to have you here in our skipcart family\n" +
               "Let's enjoy enormous amount of offers and products");
       emailSender.send(message);
    }

    /*
    *Description: Converting list of Customer to CustomerDto
    * Parameters: List<Customer>
    * Author: Santhosh Kumar
    * Date Created: 22/05/2024
    * Date Modified:
    * Returns: List<CustomerDto>
     */
    public List<CustomerDto> entityToDtoList(List<Customer> cusList){
        resultDtoList=cusList
                .stream()
                .map(cus->convertingEntities((T)cus))
                .map(cus->(CustomerDto)cus)
                .collect(Collectors.toList());
        return resultDtoList;
    }



}
