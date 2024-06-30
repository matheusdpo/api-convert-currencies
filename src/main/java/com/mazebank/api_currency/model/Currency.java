package com.mazebank.api_currency.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Currency {
    protected Double iva;
    protected Double fee;
    protected Double amount;
    protected Double total;

    public Double setTotal(Double amount) {
        this.total = amount + fee + iva;
        return Double.parseDouble(String.format("%.2f", this.total));
    }
}
