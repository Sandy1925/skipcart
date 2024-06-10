package com.sk.skipkart.products.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SellerDto {

    private String name;
    private String code;
    @NotNull(message="Email can't be empty")
    private String email;
    @NotNull(message="Password can't be empty")
    private String password;
    @NotNull(message="Account details can't be empty")
    private String account;
    private long contact;

}
