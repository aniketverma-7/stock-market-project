package com.project.stock.dto.stocks.chart;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map;

import lombok.Data;

import java.util.Map;

@Data
public class ChartResponse {
    private String id;
    private String type;
    private Map<String, StockInfo> data;

    @Data
    public static class StockInfo {
        private double open;
        private double high;
        private double low;
        private double close;
        private long volume;
        private int adj;
    }
}
