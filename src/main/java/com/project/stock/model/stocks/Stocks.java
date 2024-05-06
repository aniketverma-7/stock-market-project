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

    private String sector;

    private String exchange;

    private BigDecimal latestPrice;

    private BigDecimal prevClosePrice;

    private BigDecimal openPrice;

    private BigDecimal highPrice;

    private BigDecimal lowPrice;

    private Integer volume;

    private BigDecimal marketCap;

    private BigDecimal peRatio;

    private BigDecimal dividendYield;

    private BigDecimal eps;

    private BigDecimal beta;

    private Timestamp lastUpdated;
}
