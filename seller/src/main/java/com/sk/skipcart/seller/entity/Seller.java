package com.sk.skipcart.seller.entity;

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
public class Seller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable=true,unique=true)
    private String code;

    @Column(nullable=false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable=false)
    private String account;

    @Column(nullable=true)
    private long contact;

    @Override
    public String toString(){
        return "Seller: "+this.code+" "+this.name+" "+this.email
                +" "+this.account;
    }

}
