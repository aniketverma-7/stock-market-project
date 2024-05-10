package com.project.stock.service;

import com.project.stock.dto.stocks.portfolio.PortfolioResponse;
import com.project.stock.dto.stocks.portfolio.StockCall;
import com.project.stock.exception.GlobalExceptionHandler;
import com.project.stock.model.stocks.Portfolio;

import java.util.List;


public interface PortfolioService {
    Boolean buyStock(StockCall buyStock) throws GlobalExceptionHandler;
    Boolean sellStock(StockCall sellStock) throws GlobalExceptionHandler;

    List<PortfolioResponse> getAllByUser(String email);
}
