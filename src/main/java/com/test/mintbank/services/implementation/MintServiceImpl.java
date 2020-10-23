package com.test.mintbank.services.implementation;


import com.test.mintbank.dtos.CardDetails;
import com.test.mintbank.services.MIntService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private RestTemplate restTemplate;



    @Override
    public CardDetails getCardDetails(Long cardNumber) {
        CardDetails cardDetails = new CardDetails();
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
                return response.getBody();
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
