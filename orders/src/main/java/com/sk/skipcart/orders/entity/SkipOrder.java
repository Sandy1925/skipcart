package com.sk.skipcart.orders.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SkipOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = true)
    private String code;
    @Column(nullable = false)
    private String customerCode;
    @Column(nullable = false)
    private String productCode;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = true)
    private double total;
    @Column(nullable = true)
    private LocalDate orderPlacedDate;

    @Override
    public String toString(){
        return "code: "+this.code+"customerCode: "+this.customerCode
                +"productCode: " +this.productCode+"quantity: "+this.quantity
                +"total: "+this.total+"orderPlacedDate: "+this.orderPlacedDate;

    }
}
