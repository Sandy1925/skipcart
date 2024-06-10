package com.sk.skipcart.seller.service;

import com.sk.skipcart.seller.dto.SellerDto;
import com.sk.skipcart.seller.entity.Seller;
import com.sk.skipcart.seller.exceptions.SellerException;
import com.sk.skipcart.seller.repository.ISellerRepository;
import com.sk.skipcart.seller.utils.SellerUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64.Encoder;
import java.util.List;

import static com.sk.skipcart.seller.exceptions.SellerException.SellerErrors.SELLER_ALREADY_EXISTS;
import static com.sk.skipcart.seller.exceptions.SellerException.SellerErrors.SELLER_NOT_FOUND;

@Service
public class ImpSellerService implements ISellerService{
    @Autowired
    private Seller result;
    @Autowired
    private SellerDto resultDto;
    @Autowired
    private SellerUtils sellerUtils;
    @Autowired
    private ISellerRepository sellerRepo;
    @Autowired
    private Encoder encode;
    @Autowired
    private Logger logger;

    private List<Seller> resultList= new ArrayList<>();
    private List<SellerDto> resultDtoList= new ArrayList<>();

    /*
    *Description: Adding new Seller to the database
    * Author: Santhosh Kumar
    * Params: data:: SellerDto
    * Returns: SellerDto
    * Date Created: 30/05/2024
    * Date modified:
     */

    @Override
    public SellerDto newSeller(SellerDto data) {
            if(sellerUtils.checkSellerAlreadyExists(data.getEmail())){
                logger.warn("Throwing Seller already exists");
                throw new SellerException(SELLER_ALREADY_EXISTS);
            }else{
                Seller seller=(Seller)sellerUtils.convertingEntities(data);
                logger.info("Seller: "+seller);
                seller.setCode(sellerUtils.getCode());
                seller.setPassword(encode.encodeToString(
                        seller.getPassword().getBytes()));
                seller=sellerRepo.save(seller);
                logger.info("Seller Saved: "+seller);
                resultDto=(SellerDto)sellerUtils.convertingEntities(seller);
                logger.info("Seller Dto: "+resultDto);
            }

        return resultDto;
    }

    /*
    *Description: Getting seller by code
    * Author: Santhosh Kumar
    * Params: code::String
    * Returns: resultDto:: SellerDto
    * Date Created: 04/06/2024
    * Date modified:
     */
    @Override
    public SellerDto getSellerByCode(String code) {
        if(sellerUtils.checkSellerAlreadyExists(code)){
            result=sellerRepo.findByCode(code);
            logger.info("Seller: "+result);
            resultDto=(SellerDto)sellerUtils.convertingEntities(result);
            logger.info("Result: "+resultDto);
        }else{
            logger.warn("Throwing SELLER_NOT_FOUND Exception");
            throw new SellerException(SELLER_NOT_FOUND);
        }

        return resultDto;
    }

    /*
     *Description: Getting seller by Id
     * Author: Santhosh Kumar
     * Params: id::int
     * Returns: resultDto:: SellerDto
     * Date Created: 04/06/2024
     * Date modified:
     */
    @Override
    public SellerDto getSellerById(int id) {
        result=sellerRepo.findById(id).orElseThrow(()->
                new SellerException(SELLER_NOT_FOUND));
        resultDto=(SellerDto)sellerUtils.convertingEntities(result);
        logger.info("Result: "+resultDto);
        return resultDto;
    }

    /*
     *Description: Getting seller by Account
     * Author: Santhosh Kumar
     * Params: account::String
     * Returns: resultDto:: SellerDto
     * Date Created: 04/06/2024
     * Date modified:
     */
    @Override
    public SellerDto getSellerByAccount(String account) {
        result=sellerRepo.findByAccount(account);
        logger.info("Seller: "+result);
        if(result == null){
            logger.warn("Throwing SELLER_NOT_FOUND exceptions");
            throw new SellerException(SELLER_NOT_FOUND);
        }
        resultDto=(SellerDto)sellerUtils.convertingEntities(result);
        logger.info("Result: "+resultDto);
        return resultDto;
    }

    /*
    *Description: Updating the Seller with seller code
    * Author: Santhosh Kumar
    * Params: code::String, sellerDto:: SellerDto
    * Returns: SellerDto
    * Date Created: 04/06/2024
    * Date Modified:
     */
    @Override
    public SellerDto updateSeller(String code, SellerDto sellerDto) {

            if (sellerUtils.checkSellerAlreadyExists(code)) {
                result = sellerRepo.findByCode(code);
                logger.info("Seller: "+result);
                result.setEmail(sellerDto.getEmail());
                result.setPassword(sellerDto.getPassword());
                result.setName(sellerDto.getName());
                result.setContact(sellerDto.getContact());
                result.setAccount(sellerDto.getAccount());
                result=sellerRepo.save(result);
                logger.info("Updated Seller: "+result);
                resultDto=(SellerDto)sellerUtils.convertingEntities(result);
            } else {
                logger.warn("Throwing SELLER_NOT_FOUND exception");
                throw new SellerException(SELLER_NOT_FOUND);
            }

        return resultDto;
    }

    /*
    *Description: Getting all the seller
    * Author: Santhosh kumar
    *Params: none
    *Returns: List<SellerDto>
    *Date Created: 04/06/2024
    * Date Modified:
    */
    @Override
    public List<SellerDto> getAll() {
        resultList=sellerRepo.findAll();
        if(resultList.size()==0){
            logger.warn("Throwing SELLER_NOT_FOUND due to empty list");
            throw new SellerException(SELLER_NOT_FOUND);
        }else {
            resultDtoList = sellerUtils.convertingListEntityToDto(resultList);
        }
        return resultDtoList;
    }

    @Override
    public void deleteByCode(String code) {
        logger.info("Deleting seller with code");
        sellerRepo.deleteByCode(code);
    }
}
