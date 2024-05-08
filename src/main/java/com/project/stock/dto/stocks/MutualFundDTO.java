package com.project.stock.dto.stocks;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class MutualFundDTO {
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

    private String currency;

    @JsonProperty("google_mid")
    private String googleMid;
}



