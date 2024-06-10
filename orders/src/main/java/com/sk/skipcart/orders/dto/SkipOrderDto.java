package com.sk.skipcart.orders.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SkipOrderDto {


    private String code;
    @NotEmpty(message="Customer code can't be empty")
    private String customerCode;
    @NotEmpty(message="Product code can't be empty")
    private String productCode;
    private int quantity;
    private double total;
    private LocalDate orderPlacedDate;


    @Override
    public String toString(){
        return "code: "+this.code+"customerCode: "+this.customerCode
                +"productCode: " +this.productCode+"quantity: "+this.quantity
                +"total: "+this.total+"orderPlacedDate: "+this.orderPlacedDate;

    }
}
