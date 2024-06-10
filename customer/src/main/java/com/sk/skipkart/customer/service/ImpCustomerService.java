package com.sk.skipkart.customer.service;

import com.sk.skipkart.customer.dto.CustomerDto;
import com.sk.skipkart.customer.entity.Customer;
import com.sk.skipkart.customer.exceptions.CustomerExceptions;
import com.sk.skipkart.customer.repository.ICustomerRepository;
import com.sk.skipkart.customer.utils.CustomerUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64.Encoder;
import java.util.List;

import static com.sk.skipkart.customer.exceptions.CustomerExceptions.CustomerErrors.CUSTOMER_ALREADY_EXISTS;
import static com.sk.skipkart.customer.exceptions.CustomerExceptions.CustomerErrors.CUSTOMER_NOT_FOUND;

@Service
public class ImpCustomerService implements ICustomerService{

    @Autowired
    private Customer result;
    @Autowired
    private CustomerDto resultDto;
    @Autowired
    private ICustomerRepository cusRepo;
    @Autowired
    private CustomerUtils cusUtils;
    @Autowired
    private Logger logger;
    @Autowired
    private Encoder encode;

    private List<Customer> customerList=new ArrayList<>();
    private List<CustomerDto> customerDtoList=new ArrayList<>();

    /*
    *Adding a new customer to the database
    * we will add customer code and encode password int this with the help of utility
    * functions.
    * Author:Santhosh Kumar
    * Date Created: 21/05/2024
    * Date Modified:
    * Returns: CustomerDto
     */
    @Override
    public CustomerDto newCustomer(CustomerDto cusDto)   {

            if(cusUtils.checkCustomerAlreadyExists(cusDto.getEmail())){
                throw new CustomerExceptions(CUSTOMER_ALREADY_EXISTS);

        }else{
                Customer newCus=(Customer)cusUtils.convertingEntities(cusDto);
                logger.info("CustomerDto to Customer: ",newCus);
                newCus.setCode(cusUtils.getCustomerCode());
                newCus.setPassword(encode.encodeToString(
                        newCus.getPassword().getBytes()));
                result=cusRepo.save(newCus);
                logger.info("Customer: ",result);
                resultDto=(CustomerDto)cusUtils.convertingEntities(result);
                logger.info("Customer Dto: ",resultDto);
//                cusUtils.sendEmail(resultDto.getEmail());
//                logger.info("Sending email for the user");
        }
        return resultDto;
    }
    /*
    *Description: Getting Customer by customer code
    * Parameters: String:: Customer Code
    * Author:Santhosh Kumar
    * Date Created: 21/05/2024
    * Date Modified:
    * Returns: CustomerDto
     */
    @Override
    public CustomerDto getCustomerByCode(String cusCode) {

            result = cusRepo.findByCode(cusCode);
            resultDto = (CustomerDto) cusUtils.convertingEntities(result);
            return resultDto;

    }
    /*
    *Description: Getting Customer by email id.
    * Parameter: String:: customer email
    * Author: Santhosh Kumar
    * Date Created: 21/05/2024
    * Date Modified:
    * Returns: CustomerDto
     */
    @Override
    public CustomerDto getCustomerByEmail(String email){
        if(!(cusUtils.checkCustomerAlreadyExists(email))){
            throw new CustomerExceptions(CUSTOMER_NOT_FOUND);
        }else{
            result = cusRepo.findByEmail(email);
            resultDto = (CustomerDto) cusUtils.convertingEntities(result);
            return resultDto;
        }
    }

    /*
    *Description: Getting customer by Account number
    * Parameters: String:: account
    * Author: Santhosh Kumar
    * Date Created: 22/05/2024
    * Date Modified:
    * Returns: CustomerDto
     */

    @Override
    public List<CustomerDto> getAllCustomerByAccount(String account) {
        customerList=cusRepo.findAllByAccount(account);
        customerDtoList= cusUtils.entityToDtoList(customerList);
        return customerDtoList;
    }

    /*
    *Description: Getting all the customer
    * Parameters: None
    * Author: Santhosh Kumar
    * Date Created: 22/05/2024
    * Date Modified:
    * Returns: List<CustomerDto>
     */
    @Override
    public List<CustomerDto> getAll(){
        customerList= cusRepo.findAll();
        customerDtoList=cusUtils.entityToDtoList(customerList);
        return customerDtoList;
    }

    /*
    *Description:Updating the customer
    * Parameters: String:: email, CustomerDTO:: customerDetails
    * Author: Santhosh Kumar
    * Date Created: 22/05/2024
    * Date Modified:
    * Returns : CustomerDto
     */

    @Override
    public CustomerDto updateCustomer(String code, CustomerDto cusDto) {
        if((cusUtils.checkCustomerAlreadyExists(code))){
            throw new CustomerExceptions(CUSTOMER_NOT_FOUND);
        }else{
            Customer customer=cusRepo.findByCode(code);
            customer.setName(cusDto.getName());
            customer.setEmail(cusDto.getEmail());
            customer.setPassword(encode.encodeToString(
                    cusDto.getPassword().getBytes()));
            customer.setAccount(cusDto.getAccount());
            customer.setContact(cusDto.getContact());
            result=cusRepo.save(customer);
            resultDto=(CustomerDto)cusUtils.convertingEntities(result);
        }
        return resultDto;
    }

}
