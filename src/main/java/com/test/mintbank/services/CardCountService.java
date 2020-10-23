package com.test.mintbank.services;

import com.test.mintbank.dtos.CardCountResponse;
import com.test.mintbank.models.CardCount;
import org.springframework.data.domain.Page;

import java.util.Map;

public interface CardCountService {

    CardCount getCardByBin(String bin);

    void saveCardBin(CardCount cardCount);

    CardCount updateCount(Long id);

    Page<CardCount> getAllCardBin(int pageNo, int pageSize);
}
