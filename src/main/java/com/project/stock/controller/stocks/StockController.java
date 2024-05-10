package com.project.stock.controller.stocks;

import com.project.stock.exception.GlobalExceptionHandler;
import com.project.stock.service.impl.StockServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user/stocks")
public class StockController {

    @Autowired private StockServiceImpl service;

    @GetMapping(value = "/search",params = "name")
    public ResponseEntity<?> getAllStocks(@RequestParam(name = "name") String stockName) throws GlobalExceptionHandler {
        return ResponseEntity.ok(service.searchStockByName(stockName));
    }
}
