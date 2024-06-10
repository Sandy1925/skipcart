package com.sk.skipkart.customer.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique=true,nullable=true)
    private String code;
    @Column(nullable=true)
    private String name;

    @Column(unique=true,nullable = true)
    private String email;

    @Column(nullable=false)
    private String password;

    @Column(nullable=true)
    private long contact;

    @Column(nullable=false)
    private String account;

    @Override
    public String toString(){
        return "Customer: "+Integer.toString(this.getId())+" "+this.getName()+" "+
                this.getCode()+" "+this.getEmail()+" "+Long.toString(this.getContact())
                +" "+this.account;
    }

}
