package com.test.mintbank.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardCountResponse {

    private Boolean success;
    private int size;
    private int start;
    private int limit;
    private List<Map<String,Integer>> payload;
}
