package com.mazebank.api_currency.utils;

import com.mazebank.api_currency.model.CurrenciesModel;
import com.mazebank.api_currency.model.Dolar;
import com.mazebank.api_currency.model.Euro;
import com.mazebank.api_currency.model.ExchangeRatesModel;
import org.springframework.stereotype.Component;

@Component
public class ConvertCurrenciesUtils {

    public Double iva = 0.35;
    public Double feeMazeBank = 0.05;

    private CurrenciesModel addFeesAndTaxes(CurrenciesModel currenciesModel) {

        Euro euro = new Euro();
        euro.setFee(Double.parseDouble(String.format("%.2f", currenciesModel.getEUR().getAmount() * feeMazeBank)));
        euro.setIva(Double.parseDouble(String.format("%.2f", currenciesModel.getEUR().getAmount() * iva)));
        euro.setAmount(currenciesModel.getEUR().getAmount());
        euro.setTotal(currenciesModel.getEUR().getAmount());

        Dolar dolar = new Dolar();
        dolar.setFee(Double.parseDouble(String.format("%.2f", currenciesModel.getUSD().getAmount() * feeMazeBank)));
        dolar.setIva(Double.parseDouble(String.format("%.2f", currenciesModel.getUSD().getAmount() * iva)));
        dolar.setAmount(currenciesModel.getUSD().getAmount());
        dolar.setTotal(currenciesModel.getUSD().getAmount());

        currenciesModel.setEUR(euro);
        currenciesModel.setUSD(dolar);

        return currenciesModel;
    }

    private CurrenciesModel multiplyAmounts(ExchangeRatesModel exchangeRatesModel, Double amount) {
        CurrenciesModel currenciesModelResult = new CurrenciesModel();

        Euro euro = new Euro();
        euro.setAmount(exchangeRatesModel.getConversionRates().get("EUR") * amount);

        Dolar dolar = new Dolar();
        dolar.setAmount(exchangeRatesModel.getConversionRates().get("USD") * amount);

        currenciesModelResult.setEUR(euro);
        currenciesModelResult.setUSD(dolar);

        return currenciesModelResult;
    }

    public CurrenciesModel getCurrencies(ExchangeRatesModel exchangeRatesModel, Double amount) {
        CurrenciesModel currenciesModel = multiplyAmounts(exchangeRatesModel, amount);
        return addFeesAndTaxes(currenciesModel);
    }
}
