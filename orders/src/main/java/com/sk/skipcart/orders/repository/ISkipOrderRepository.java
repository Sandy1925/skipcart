package com.sk.skipcart.orders.repository;

import com.sk.skipcart.orders.entity.SkipOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISkipOrderRepository extends JpaRepository<SkipOrder,Long> {
}
