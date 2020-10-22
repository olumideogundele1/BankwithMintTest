package com.test.mintbank.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Bank {

    private String name;
    private String url;
    private String phone;
    private String city;
}
