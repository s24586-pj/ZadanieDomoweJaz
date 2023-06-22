package com.example.demo;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class CurrencyRate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String currency;

    private int days;

    private double rate;

    private LocalDateTime queryDateTime;

    public CurrencyRate() {
    }

    public CurrencyRate(Long id, String currency, int days, double rate, LocalDateTime queryDateTime) {
        this.id = id;
        this.currency = currency;
        this.days = days;
        this.rate = rate;
        this.queryDateTime = queryDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public LocalDateTime getQueryDateTime() {
        return queryDateTime;
    }

    public void setQueryDateTime(LocalDateTime queryDateTime) {
        this.queryDateTime = queryDateTime;
    }
    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", currency='" + currency + '\'' +
                ", days=" + days +
                ", rate=" + rate +
                ", queryDateTime=" + queryDateTime +
                '}';
    }
}
