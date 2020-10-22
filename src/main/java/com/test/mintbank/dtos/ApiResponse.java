package com.test.mintbank.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

    private Boolean success;
    private CardDetails payload;
    private String message;


    public ApiResponse(Boolean success, CardDetails payload){
        this.success = success;
        this.payload = payload;
    }

    public ApiResponse(Boolean success, String message){
        this.message = message;
        this.success = success;
    }


}
