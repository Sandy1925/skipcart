package com.sk.skipcart.orders.exceptions;

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

    private String code;
    private String message;
    private String path;
    private LocalDateTime timeStamp;
}
