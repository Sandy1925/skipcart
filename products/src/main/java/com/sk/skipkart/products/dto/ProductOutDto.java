package com.sk.skipkart.products.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductOutDto {

    private String code;
    private String name;
    private String sellerCode;
    private String sellerName;
    private String description;
    private String category;
    private double price;

    @Override
    public String toString(){
        return "Code: "+this.code+"Name: "+this.name+"Description: "+"Seller: "+this.sellerName
                +this.description+"Category: "+this.category+
                "Price: "+Double.toString(this.price);
    }
}
