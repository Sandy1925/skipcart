package com.sk.skipkart.customer.repository;

import com.sk.skipkart.customer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICustomerRepository extends JpaRepository<Customer,Integer> {
        public Customer findByEmail(String email);
        public Customer findByCode(String code);
        public List<Customer> findAllByAccount(String account);

}
