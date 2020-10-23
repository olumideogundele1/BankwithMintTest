package com.test.mintbank.controllers;

import com.test.mintbank.dtos.ApiResponse;
import com.test.mintbank.services.MIntService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/")
public class MintController {

    private MIntService mIntService;

    public MintController(MIntService mIntService) {
        this.mIntService = mIntService;
    }


    @GetMapping("/card-scheme/verify/{cardId}")
    public ResponseEntity<?> getCardDetails(@PathVariable Long cardId){

        try{
            return new ResponseEntity<>(new ApiResponse(Boolean.TRUE,mIntService.getCardDetails(cardId)), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
