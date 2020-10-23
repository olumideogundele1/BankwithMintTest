package com.test.mintbank.services.implementation;


import com.test.mintbank.dtos.CardDetails;
import com.test.mintbank.dtos.MintResponse;
import com.test.mintbank.models.CardCount;
import com.test.mintbank.repositories.CardCountRepository;
import com.test.mintbank.services.CardCountService;
import com.test.mintbank.services.MIntService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class MintServiceImpl implements MIntService {

    @Value("binListUrl")
    private String binListUrl;


    private RestTemplate restTemplate;
    private CardCountService cardCountService;

    public MintServiceImpl(RestTemplate restTemplate, CardCountService cardCountService) {
        this.restTemplate = restTemplate;
        this.cardCountService = cardCountService;
    }

    @Override
    public MintResponse getCardDetails(Long cardNumber) {
        MintResponse cardDetails = new MintResponse();
        try{
            final String uri = "https://lookup.binlist.net/"+cardNumber;
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", MediaType.APPLICATION_JSON_VALUE);
            headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
            headers.add("Access-Control-Allow-Origin", "*");
            headers.add("User-Agent", "Mozilla");

            HttpEntity<?> http = new HttpEntity<>(headers);
            ResponseEntity<CardDetails> response = restTemplate.exchange(uri, HttpMethod.GET,http,CardDetails.class);
            if(response.getStatusCode().is2xxSuccessful() && response.getBody() != null){
                //create number of Hits
                CardCount cardCount = cardCountService.getCardByBin(String.valueOf(cardNumber));
                if(cardCount != null){
                    //if bin exist increase the count
                    log.info("Card Count " + cardCount.getCount());
                    cardCount = cardCountService.updateCount(cardCount.getId());
                    log.info("Card Count " + cardCount.getCount());

                }else{
                    //else create bin
                    CardCount newCardCount = new CardCount();
                    newCardCount.setBin(String.valueOf(cardNumber));
                    cardCountService.saveCardBin(newCardCount);
                }

                return new MintResponse(response.getBody().getScheme(),response.getBody().getType(),
                        response.getBody().getBank().getName(),response.getBody().getPrepaid(),response.getBody().getBrand());

            }else{
                log.info("Result Not Found");
            }
        }catch (HttpClientErrorException e){
            log.info("Client Exception "  + e.getMessage());
        }
        catch (Exception e){
            log.info("Server Exception " + e.getMessage());
        }

        return cardDetails;
    }
}
