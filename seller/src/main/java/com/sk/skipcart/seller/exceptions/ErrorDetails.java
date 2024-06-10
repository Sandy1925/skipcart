package com.sk.skipcart.seller.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetails {

    private String message;
    private String code;
    private String path;
    private LocalDateTime timeStamp;
}
