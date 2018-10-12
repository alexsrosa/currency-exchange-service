package com.example.microservices.currencyexchangeservice.controller;

import com.example.microservices.currencyexchangeservice.model.ExchangeValue;
import com.example.microservices.currencyexchangeservice.repository.ExchangeValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    private final Environment environment;
    private final ExchangeValueRepository exchangeValueRepository;

    @Autowired
    public CurrencyExchangeController(Environment environment,
                                      ExchangeValueRepository exchangeValueRepository) {
        this.environment = environment;
        this.exchangeValueRepository = exchangeValueRepository;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to){
        ExchangeValue exchangeValue = exchangeValueRepository.findByFromAndTo(from, to);
        exchangeValue.setPort(Integer.valueOf(environment.getProperty("local.server.port")));
        return exchangeValue;
    }
}