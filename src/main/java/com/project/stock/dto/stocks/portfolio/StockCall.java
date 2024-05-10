package com.project.stock.dto.stocks.portfolio;

import lombok.Data;

@Data
public class StockCall {
    String email;
    String symbol;
    int quantity;
}
