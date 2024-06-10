package com.sk.skipkart.products.service;

import com.sk.skipkart.products.dto.ProductDto;
import com.sk.skipkart.products.dto.ProductOutDto;
import com.sk.skipkart.products.entity.Product;
import com.sk.skipkart.products.exceptions.ProductException;
import com.sk.skipkart.products.repository.IProductRepository;
import com.sk.skipkart.products.utils.ProductUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.sk.skipkart.products.exceptions.ProductException.ProductErrors.PRODUCT_ALREADY_EXISTS;
import static com.sk.skipkart.products.exceptions.ProductException.ProductErrors.PRODUCT_NOT_FOUND;

@Service
public class ImpProductService implements IProductService{
    @Autowired
    private IProductRepository prdRepo;
    @Autowired
    private Product result;
    @Autowired
    private ProductDto prdDto;
    @Autowired
    private ProductOutDto prdOutDto;
    @Autowired
    private ProductUtils productUtils;
    @Autowired
    private Logger logger;
    @Autowired
    private RestTemplate restTemplate;

    private String sellerUrl="http://localhost:8082/seller/";
    private List<Product> resultList= new ArrayList<>();
    private List<ProductOutDto> resultDtoList=new ArrayList<>();



    /*
    *Description: Saving new Product to the database
    * Author: Santhosh Kumar
    * Params: prdDto::ProductDto
    * Returns: ProductOutDto
    * Date Created: 04/06/2024
    * Date Modified:
     */
    @Override
    public ProductOutDto newProduct(ProductDto prdDto) {
        if(productUtils.checkAlreadyExists(prdDto.getCode())){
            logger.warn("Throwing product already exists exception for code: "+prdDto.getCode());
            throw new ProductException(PRODUCT_ALREADY_EXISTS);
        }else{
            Product product=new Product();
            product.setCode(productUtils.getCode());
            product.setName(prdDto.getName());
            product.setCategory(prdDto.getCategory());
            product.setDescription(prdDto.getDescription());
            product.setSellerCode(prdDto.getSellerCode());
            product.setStock(prdDto.getStock());
            product.setPrice(prdDto.getPrice());
            product=prdRepo.save(product);
            logger.info("Saved Product: "+product);
            prdOutDto=(ProductOutDto)productUtils.convertingEntities(product);
            prdOutDto.setSellerName(productUtils.getSellerName(product.getSellerCode()));


        }
        return prdOutDto;
    }

    /*
    *Description: getting all the products
    *Author: Santhosh kumar
    *Params: none
    *Returns: List<ProductOutDto>
    *Date Created: 04/06/2024
    *Date modified:
     */
    @Override
    public List<ProductOutDto> getAll() {
        resultList=prdRepo.findAll();
        resultDtoList=productUtils.listEntityToDto(resultList);
        resultDtoList=resultDtoList
                .stream()
                .map((prod)-> {
            prod.setSellerName(productUtils.getSellerName(prod.getSellerCode()));
            return prod;
        }).collect(Collectors.toList());
        logger.info("Product list: "+resultDtoList);
        return resultDtoList;
    }

    /*
     *Description: getting product by code
     *Author: Santhosh kumar
     *Params: code::String
     *Returns: ProductOutDto
     *Date Created: 04/06/2024
     *Date modified:
     */
    @Override
    public ProductOutDto getByCode(String code) {
        result=prdRepo.findByCode(code);
        logger.info("Product of the code: "+result);
        if(result==null){
            logger.warn("Product does not exist for the code: "+code);
            throw new ProductException(PRODUCT_NOT_FOUND);

        }
        prdOutDto=(ProductOutDto)productUtils.convertingEntities(result);
        prdOutDto.setSellerName(productUtils.getSellerName(prdOutDto.getSellerCode()));
        logger.info("Product Out Dto: "+prdOutDto);
        return prdOutDto;
    }

    /*
     *Description: getting all the products by category
     *Author: Santhosh kumar
     *Params: category::String
     *Returns: List<ProductOutDto>
     *Date Created: 04/06/2024
     *Date modified:
     */
    @Override
    public List<ProductOutDto> getAllByCateGory(String category) {
        resultList=prdRepo.findAllByCategory(category);
        logger.info("list of products by category: "+category+": "+resultList );
        if(resultList.size()==0){
            logger.warn("Throwing Product not found for Empty List of Products");
            throw new ProductException(PRODUCT_NOT_FOUND);
        }
        resultDtoList=productUtils.listEntityToDto(resultList);
        resultDtoList=resultDtoList
                .stream()
                .map((prod)-> {
                    prod.setSellerName(productUtils.getSellerName(prod.getSellerCode()));
                    return prod;
                }).collect(Collectors.toList());
        return resultDtoList;
    }

    /*
     *Description: getting all the products by seller code
     *Author: Santhosh kumar
     *Params: sellerCode:: String
     *Returns: List<ProductOutDto>
     *Date Created: 04/06/2024
     *Date modified:
     */
    @Override
    public List<ProductOutDto> getAllBySellerCode(String sellerCode) {
        resultList=prdRepo.findAllBySellerCode(sellerCode);
        logger.info("list of products by sellercode: "+sellerCode+": "+resultList );
        if(resultList.size()==0){
            logger.warn("Throwing Product not found for Empty List of Products");
            throw new ProductException(PRODUCT_NOT_FOUND);
        }
        resultDtoList=productUtils.listEntityToDto(resultList);
        resultDtoList=resultDtoList
                .stream()
                .map((prod)-> {
                    prod.setSellerName(productUtils.getSellerName(prod.getSellerCode()));
                    return prod;
                }).collect(Collectors.toList());
        return resultDtoList;
    }
    /*
    *Description: Getting all the prodcuts of a particular stock
    *Author: Santhosh kumar
    *Params: stock:: int
    *Returns: List<ProductOutDto>
    *Date Created; 04/06/2024
    *Date Modified:
     */
    @Override
    public List<ProductOutDto> getAllByStock(int stock) {
        resultList=prdRepo.findAllByStock(stock);
        logger.info("ProductList for stock: "+stock+" : "+resultList);
        if(resultList.size()==0){
            logger.warn("throwing prodcut not found for empty list");
            throw new ProductException(PRODUCT_NOT_FOUND);
        }
        resultDtoList=productUtils.listEntityToDto(resultList);
        resultDtoList=resultDtoList
                .stream()
                .map((prod)-> {
                    prod.setSellerName(productUtils.getSellerName(prod.getSellerCode()));
                    return prod;
                }).collect(Collectors.toList());
        return resultDtoList;
    }

    @Override
    public List<ProductOutDto> getAllByPrice(double price) {
        resultList=prdRepo.findAllByPrice(price);
        logger.info("ProductList for price: "+price+" : "+resultList);
        if(resultList.size()==0){
            logger.warn("throwing prodcut not found for empty list");
            throw new ProductException(PRODUCT_NOT_FOUND);
        }
        resultDtoList=productUtils.listEntityToDto(resultList);
        resultDtoList=resultDtoList
                .stream()
                .map((prod)-> {
                    prod.setSellerName(productUtils.getSellerName(prod.getSellerCode()));
                    return prod;
                }).collect(Collectors.toList());
        return resultDtoList;
    }
    /*
    *Description: Updating product based on the code
    *Author: Santhosh Kumar
    *Params: code::String, prdDto::ProductDto
    *Returns ProductOutDto
    *Date Created: 04/06/2024
    *Date Modified:
     */
    @Override
    public ProductOutDto updateProduct(String code, ProductDto prdDto) {
        if(productUtils.checkAlreadyExists(code)){
            result=prdRepo.findByCode(code);
            result.setName(prdDto.getName());
            result.setCategory(prdDto.getCategory());
            result.setDescription(prdDto.getCategory());
            result.setSellerCode(prdDto.getSellerCode());
            result.setStock(prdDto.getStock());
            result.setPrice(prdDto.getPrice());
            result=prdRepo.save(result);
            logger.info("Saved Product: "+result);
            prdOutDto=(ProductOutDto)productUtils.convertingEntities(result);
            prdOutDto.setSellerName(productUtils.getSellerName(prdOutDto.getSellerCode()));
            logger.info("returning Product: "+prdOutDto);
        }else{
            logger.warn("throwing product not found of rth code: "+code);
            throw new ProductException(PRODUCT_NOT_FOUND);
        }
        return prdOutDto;
    }

    /*
    *Description: Deleting product by code
    *Author: Santhosh Kumar
    *Params: code::String
    *Returns: none
    *Date Created: 04/06/2024
    *Date Modified:
     */
    @Override
    public void deleteByCode(String code) {
        prdRepo.deleteByCode(code);
        logger.info("Deleting the product of code: "+code);
    }
}
