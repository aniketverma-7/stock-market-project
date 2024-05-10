package com.project.stock.service;

import com.project.stock.dto.stocks.chart.ChartRequest;
import com.project.stock.dto.stocks.chart.ChartResponse;
import com.project.stock.exception.GlobalExceptionHandler;

import java.io.IOException;

public interface ChartService {

    Object getChart(String symbol) throws IOException;
}
