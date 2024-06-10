package com.sk.skipkart.customer.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {

    private int id;
    private String code;
    @NotBlank(message = "Name should not be blank")
    private String name;
    @NotBlank(message = "Email should not be blank")
    private String email;
    @NotBlank(message = "Password should not be blank")
    private String password;
    private long contact;
    private String account;

}
