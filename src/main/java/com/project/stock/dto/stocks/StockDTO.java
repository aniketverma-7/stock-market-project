package com.project.stock.dto.stocks;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {
    private String symbol;
    private String name;
    private String type;
    private BigDecimal price;
    private BigDecimal change;
    @JsonProperty("change_percent")
    private BigDecimal changePercent;

    @JsonProperty("previous_close")
    private BigDecimal previousClose;

    @JsonProperty("last_update_utc")
    private String lastUpdateUtc;

    @JsonProperty("country_code")
    private String countryCode;

    private String exchange;

    @JsonProperty("exchange_open")
    private String exchangeOpen;

    @JsonProperty("exchange_close")
    private String exchangeClose;

    private String timezone;

    @JsonProperty("utc_offset_sec")
    private Integer utcOffsetSec;

    private String currency;

    @JsonProperty("google_mid")
    private String googleMid;

    // Constructors, getters, and setters
}
