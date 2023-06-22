package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/currencyRate")
public class CurrencyRateController {

    private final CurrencyRateService currencyRateService;
    @Autowired
    public CurrencyRateController(CurrencyRateService currencyRateService) {
        this.currencyRateService = currencyRateService;
    }

    @GetMapping("/{currency}")
    public ResponseEntity<Double> getAverageRate(
            @PathVariable("currency")
            String currency,
            @RequestParam(value = "days", defaultValue = "1")
            int days
    ) {
        double averageRate = currencyRateService.calculateAverageRate(currency, days);
        return ResponseEntity.ok(averageRate);
    }
}
