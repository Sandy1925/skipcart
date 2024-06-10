package com.sk.skipcart.orders.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SkipCartDto {


    @NotEmpty(message="Customer Code can't be empty")
    private String customerCode;
    @NotEmpty(message="Customer Code can't be empty")
    private String productCode;
    private int quantity;

    @Override
    public String toString(){
        return "customerCode: "+this.customerCode+"productCode: "+this.productCode
                +"Quantity: "+this.quantity;
    }
}
