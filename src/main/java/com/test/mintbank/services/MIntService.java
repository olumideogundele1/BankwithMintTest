package com.test.mintbank.services;


import com.test.mintbank.dtos.MintResponse;

public interface MIntService {

    MintResponse getCardDetails(Long cardNumber);
}
