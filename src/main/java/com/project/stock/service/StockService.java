package com.project.stock.service;

import com.project.stock.exception.GlobalExceptionHandler;

import java.util.List;

public interface StockService {
    public List<?> searchStockByName(String name) throws GlobalExceptionHandler;
    public Double getCurrentPrice(String name) throws GlobalExceptionHandler;
}
