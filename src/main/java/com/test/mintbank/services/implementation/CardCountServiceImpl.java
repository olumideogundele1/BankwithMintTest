package com.test.mintbank.services.implementation;

import com.test.mintbank.dtos.CardCountDTO;
import com.test.mintbank.models.CardCount;
import com.test.mintbank.repositories.CardCountRepository;
import com.test.mintbank.services.CardCountService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class CardCountServiceImpl implements CardCountService {

    private CardCountRepository cardCountRepository;
    private ModelMapper modelMapper;

    public CardCountServiceImpl(CardCountRepository cardCountRepository, ModelMapper modelMapper) {
        this.cardCountRepository = cardCountRepository;
        this.modelMapper = modelMapper;
    }

    //convertToDTO
    private CardCountDTO convertToDTO(CardCount cardCount){
        return modelMapper.map(cardCount,CardCountDTO.class);
    }

    private CardCount convertToEntity(CardCountDTO cardCountDTO){
        return modelMapper.map(cardCountDTO,CardCount.class);
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

    @Override
    public Page<CardCount> getAllCardBin(int pageNo, int pageSize) {
        List<CardCountDTO> cardCountDTOList = new ArrayList<>();
        Pageable pageable = PageRequest.of(pageNo,pageSize);
        Page<CardCount> list = cardCountRepository.findAll(pageable);
//        cardCountDTOList = list.stream().map(this::convertToDTO).collect(Collectors.toList());
//        Page<CardCountDTO> page = cardCountDTOList.
        return list;
    }
}
