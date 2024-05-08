package com.project.stock.model.stocks;


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
public class Stocks {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long stockId;

    private String symbol;

    private String companyName;

    private String type;

    private BigDecimal price;

    private BigDecimal change;

    private BigDecimal changePercent;

    private BigDecimal previousClose;

    private Timestamp lastUpdateUtc;

    private String countryCode;

    private String exchange;

    private Timestamp exchangeOpen;

    private Timestamp exchangeClose;

    private String timezone;

    private Integer utcOffsetSec;

    private String currency;
}
