package com.project.stock.dto.stocks.watchlist;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.stock.dto.stocks.StockDTO;
import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WatchlistResponse {
    private int code;
    private String email;

    @JsonProperty("stock")
    private StockDTO stockDTO;
}
