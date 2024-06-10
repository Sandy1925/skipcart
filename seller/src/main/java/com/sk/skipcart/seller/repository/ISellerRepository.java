package com.sk.skipcart.seller.repository;

import com.sk.skipcart.seller.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISellerRepository extends JpaRepository<Seller,Integer> {

    public Seller findByEmail(String email);
    public Seller findByCode(String code);
    public Seller findByAccount(String account);
    public void deleteByCode(String code);

}
