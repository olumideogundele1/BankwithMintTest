package com.test.mintbank.services.implementation;


import com.test.mintbank.dtos.CardDetails;
import com.test.mintbank.services.MIntService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Slf4j
public class MintServiceImpl implements MIntService {

    @Autowired
    private RestTemplate restTemplate;

    @Value("")

    @Override
    public CardDetails getCardDetails(Long cardNumber) {

        final String uri = "http://lookup.binlist.net/"+cardNumber;
        HttpHeaders headers = new HttpHeaders();
        headers
        return null;
    }
}
