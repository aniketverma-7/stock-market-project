package com.project.stock.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.stock.dto.stocks.StockMutualDTO;
import com.project.stock.exception.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface StockService {
    public List<?> searchStockByName(String name) throws GlobalExceptionHandler;
}
