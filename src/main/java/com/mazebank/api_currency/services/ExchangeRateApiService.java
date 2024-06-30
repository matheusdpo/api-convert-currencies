package com.mazebank.api_currency.services;

import com.mazebank.api_currency.model.ExchangeRatesModel;
import com.mazebank.api_currency.utils.SerializationUtils;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class ExchangeRateApiService {


    @Value("${exchange.rate.api.url}")
    private String exchangeRateApiUrl;

    @Value("${exchange.rate.api.key}")
    private String exchangeRateApiKey;

    @Autowired
    private SerializationUtils serializationUtils;

    public ExchangeRatesModel getExchangeRates() {
        String url = exchangeRateApiUrl + exchangeRateApiKey + "/latest/BRL";
        try {
            HttpResponse<String> response = Unirest.get(url)
                    .asString();

            String responseStr = response.getBody();

            return serializationUtils.serialize(responseStr, ExchangeRatesModel.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
