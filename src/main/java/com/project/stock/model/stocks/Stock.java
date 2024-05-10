package com.project.stock.model.stocks;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stocks")
public class Stock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stock_id")
    private Long stockId;
    public String symbol;

    @Column(name = "company_name")
    public String name;

    @Column(name = "stock_type")
    public String type;
    public Double price;

    @Column(name = "change_in_price")
    public Double change;

    @Column(name = "stock_exchange")
    public String exchange;
    public String timezone;
    public String currency;

    @Column(name = "change_percent")
    public Double changePercent;

    @Column(name = "previous_close")
    public Double previousClose;

    @Column(name = "last_update_utc")
    public Timestamp lastUpdateUtc;

    @Column(name = "country_code")
    public String countryCode;

    @Column(name = "exchange_open")
    public Timestamp exchangeOpen;

    @Column(name = "exchange_close")
    public Timestamp exchangeClose;

    @Column(name = "utc_offset_sec")
    public Integer utcOffsetSec;

    @Column(name = "google_mid")
    public String googleMid;

}
