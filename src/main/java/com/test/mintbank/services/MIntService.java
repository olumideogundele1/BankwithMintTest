package com.test.mintbank.services;

import com.test.mintbank.dtos.CardDetails;

public interface MIntService {

    CardDetails getCardDetails(Long cardNumber);
}
