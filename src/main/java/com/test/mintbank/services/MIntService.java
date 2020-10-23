package com.test.mintbank.services;


import com.test.mintbank.dtos.MintResponse;
import com.test.mintbank.models.CardCount;
import org.springframework.data.domain.Page;

public interface MIntService {

    MintResponse getCardDetails(Long cardNumber);

    Page<CardCount> getAllCardCount(int pageNo,int pageSize);
}
