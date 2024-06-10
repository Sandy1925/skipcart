package com.sk.skipkart.products.entity;

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
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable=false)
    private String sellerCode;

    @Column(nullable=true)
    private String code;

    @Column(nullable=false)

    private String name;

    @Column(nullable=false)

    private String description;

    @Column(nullable=false)
    private String category;

    @Column(nullable=false)
    private int stock;

    @Column(nullable=false)
    private double price;

    @Override
    public String toString(){
        return "Code: "+this.code+"Name: "+this.name+"Description: "
                +this.description+"Category: "+this.category+"Stock: "+Integer.toString(this.stock)
                +"Price: "+Double.toString(this.price);
    }
}
