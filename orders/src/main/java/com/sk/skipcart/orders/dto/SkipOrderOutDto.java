package com.sk.skipcart.orders.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SkipOrderOutDto {

    private String code;
    private String customerCode;
    private String customerName;
    private String productCode;
    private String productName;
    private String sellerCode;
    private String sellerName;
    private int quantity;
    private double total;
    private LocalDate orderPlacedDate;

}
