package com.mazebank.api_currency.model;

public class Currency {
    private Double iva;
    private Double fee;
    private Double amount;
    private Double total;

    public Double setTotal(Double amount) {
        this.total = amount + fee + iva;
        return Double.parseDouble(String.format("%.2f", this.total));
    }
}
