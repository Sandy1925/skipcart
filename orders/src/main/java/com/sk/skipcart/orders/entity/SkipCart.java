package com.sk.skipcart.orders.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SkipCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable=false)
    private String customerCode;
    @Column(nullable=false)
    private String productCode;
    @Column(nullable=false)
    private int quantity;

    @Override
    public String toString(){
        return "customerCode: "+this.customerCode+"productCode: "+this.productCode
                +"Quantity: "+this.quantity;
    }
}
