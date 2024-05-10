package com.project.stock.dto.stocks.portfolio;

import com.project.stock.dto.stocks.StockDTO;
import com.project.stock.model.stocks.Stock;
import lombok.Data;

@Data
public class PortfolioResponse {
    private StockDTO stock;
    private Integer quantity;
    private Double avgPurchasePrice;
    private Double totalInvestment;
    private Double currentPrice;
    private Double totalValue;
}
