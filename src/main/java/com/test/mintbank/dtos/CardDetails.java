package com.test.mintbank.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDetails {

    private String scheme;
    private String type;
    private Bank bank;
    private Boolean prepaid;
    private String brand;

}
