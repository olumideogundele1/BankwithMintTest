package com.test.mintbank.services.implementation;

import com.test.mintbank.models.CardCount;
import com.test.mintbank.repositories.CardCountRepository;
import com.test.mintbank.services.CardCountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
public class CardCountServiceImpl implements CardCountService {

    private CardCountRepository cardCountRepository;

    public CardCountServiceImpl(CardCountRepository cardCountRepository) {
        this.cardCountRepository = cardCountRepository;
    }

    @Override
    public CardCount getCardByBin(String bin) {
        return cardCountRepository.findByBin(bin);
    }

    @Override
    public void saveCardBin(CardCount cardCount) {
        try{
            cardCountRepository.save(cardCount);
            log.info("Card Bin saved successfully!!!");
        }catch (Exception e){
            log.error("Error in creating card bin");
        }

    }

    @Override
    @Modifying
    @Transactional
    public CardCount updateCount(Long id) {
        CardCount cardCount = new CardCount();
        try{
            cardCount = cardCountRepository.getOne(id);
            cardCount.setCount(cardCount.getCount() + 1);
            saveCardBin(cardCount);

        }catch (Exception e){
            log.info("Error in updating card bin");
        }

        return cardCount;
    }
}
