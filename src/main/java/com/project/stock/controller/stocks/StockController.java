package com.project.stock.controller.stocks;

import com.project.stock.dto.stocks.StockMutualDTO;
import com.project.stock.exception.GlobalExceptionHandler;
import com.project.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/user/stocks")
public class StockController {

    @Autowired private StockService service;

    @GetMapping(value = "/search",params = "name")
    public ResponseEntity<StockMutualDTO> getAllStocks(@RequestParam(name = "name") String stockName) throws GlobalExceptionHandler {
        return ResponseEntity.ok(service.searchStockByName(stockName));
    }
}
