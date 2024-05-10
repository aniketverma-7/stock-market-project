package com.project.stock.dto.stocks.portfolio;

import lombok.Data;

@Data
public class BuyStock {
    String email;
    String symbol;
    int quantity;
    double price;
}
