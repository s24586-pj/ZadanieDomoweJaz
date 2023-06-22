package com.example.demo;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
public class CurrencyRateService {
    private static final String NBP_API_URL = "http://api.nbp.pl/api/exchangerates/rates/A/{currency}/last/{days}/?format=json";

    private final CurrencyRateRepository currencyRateRepository;
    private final RestTemplate restTemplate;

    @Autowired
    public CurrencyRateService(CurrencyRateRepository currencyRateRepository, RestTemplate restTemplate) {
        this.currencyRateRepository = currencyRateRepository;
        this.restTemplate = restTemplate;
    }

    public double calculateAverageRate(String currency, int days) {

        String apiUrl = NBP_API_URL.replace("{currency}", currency).replace("{days}", String.valueOf(days));

        ResponseEntity<JsonNode> responseEntity = restTemplate.getForEntity(apiUrl, JsonNode.class);
        JsonNode response = responseEntity.getBody();

        double sum = 0.0;
        int count = 0;

        for (JsonNode rateNode : response.get("rates")) {
            double mid = rateNode.get("mid").asDouble();
            sum += mid;
            count++;
        }

        double average = count > 0 ? sum / count : 0.0;

        CurrencyRate currencyRate = new CurrencyRate();
        currencyRate.setCurrency(currency);
        currencyRate.setDays(days);
        currencyRate.setRate(average);
        currencyRate.setQueryDateTime(LocalDateTime.now());

        currencyRateRepository.save(currencyRate);

        return average;
    }
}
