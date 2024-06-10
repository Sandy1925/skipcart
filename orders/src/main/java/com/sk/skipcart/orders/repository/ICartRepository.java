package com.sk.skipcart.orders.repository;

import com.sk.skipcart.orders.entity.SkipCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICartRepository extends JpaRepository<SkipCart,Long> {

    public List<SkipCart> findAllByCustomerCode(String customerCode);
    public void deleteByCustomerCode(String code);

}
