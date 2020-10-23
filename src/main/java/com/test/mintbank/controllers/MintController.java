package com.test.mintbank.controllers;

import com.test.mintbank.dtos.ApiResponse;
import com.test.mintbank.dtos.CardCountResponse;
import com.test.mintbank.models.CardCount;
import com.test.mintbank.services.MIntService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @GetMapping("/card-scheme/stats")
    public ResponseEntity<?> getAllCardCount(@RequestParam int start, @RequestParam int limit){
        try{
            Page<CardCount> page = mIntService.getAllCardCount(start,limit);
            CardCountResponse cardCountResponse = new CardCountResponse();
            cardCountResponse.setSize(page.getSize());
            List<Map<String,Integer>> list = page.get().map(x -> {
                Map<String,Integer> map = new HashMap<>();
                map.put(x.getBin(),x.getCount());
                return map;
            }).collect(Collectors.toList());
            cardCountResponse.setPayload(list);
            cardCountResponse.setSuccess(Boolean.TRUE);
            cardCountResponse.setLimit(limit);
            cardCountResponse.setStart(start);
            return new ResponseEntity<>(cardCountResponse,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
