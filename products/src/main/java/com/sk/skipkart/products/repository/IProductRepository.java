package com.sk.skipkart.products.repository;

import com.sk.skipkart.products.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IProductRepository extends JpaRepository<Product,Long> {

    public Product findByCode(String code);
    public List<Product>findAllByCategory(String category);
    public List<Product> findAllBySellerCode(String sellerCode);
    public List<Product> findAllByStock(int stock);
    public List<Product> findAllByPrice(double price);
    public void deleteByCode(String code);
}
