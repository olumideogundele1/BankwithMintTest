package com.test.mintbank.services;

import com.test.mintbank.models.CardCount;

public interface CardCountService {

    CardCount getCardByBin(String bin);

    void saveCardBin(CardCount cardCount);

    CardCount updateCount(Long id);
}
