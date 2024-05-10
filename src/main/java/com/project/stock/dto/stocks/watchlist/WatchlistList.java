package com.project.stock.dto.stocks.watchlist;

import com.project.stock.model.stocks.Stock;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WatchlistList {

    private int code;
    private List<Stock> stocks;
}
