package com.test.mintbank.dtos;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

public class ApiResponse {

    private Boolean success;
    private Object payload;


    public ApiResponse(){}


    public ApiResponse(Boolean success, Object payload){
        this.success = success;
        this.payload = payload;
    }

    public ApiResponse(Boolean success){
        this.success = success;
    }


}
