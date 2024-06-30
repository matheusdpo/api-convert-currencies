package com.mazebank.api_currency.resources;

import com.mazebank.api_currency.model.CurrenciesModel;
import com.mazebank.api_currency.model.ExchangeRatesModel;
import com.mazebank.api_currency.services.ExchangeRateApiService;
import com.mazebank.api_currency.utils.ConvertCurrenciesUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/buy")
public class MoedaConvertidaResource {

    @Autowired
    private ExchangeRateApiService exchangeRateApiService;

    @Autowired
    ConvertCurrenciesUtils convertCurrenciesUtils;

    @GetMapping(value = "/{amount}")
    public ResponseEntity<CurrenciesModel> getCurrenciesConvertes(@PathVariable Double amount) {
        ExchangeRatesModel exchangeRatesModel = exchangeRateApiService.getExchangeRates();

        CurrenciesModel currenciesModel = convertCurrenciesUtils.getCurrencies(exchangeRatesModel, amount);
        return ResponseEntity.ok().body(currenciesModel);
    }
}
