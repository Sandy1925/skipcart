package com.sk.skipkart.products.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    private String code;
    @NotEmpty(message="Name should not be empty")
    private String name;
    @NotEmpty(message="SellerCode should not be empty")
    private String sellerCode;
    @NotEmpty(message="Description should not be empty")
    private String description;
    @NotEmpty(message="Category should not be empty")
    private String category;
    private int stock;
    private double price;

    @Override
    public String toString(){
        return "Code: "+this.code+"Name: "+this.name+"Description: "
                +this.description+"Category: "+this.category+"Stock: "+Integer.toString(this.stock)
                +"Price: "+Double.toString(this.price);
    }
}
