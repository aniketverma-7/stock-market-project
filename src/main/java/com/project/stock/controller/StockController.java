package com.project.stock.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/user")
public class StockController {


    @PostMapping("/stocks")
    public ResponseEntity<?> getAllStocks(){
        System.out.println("asdk");
        ArrayList<String> stocks = new ArrayList<>();
        stocks.add("GOOG");
        stocks.add("GOOG");
        stocks.add("GOOG");
        stocks.add("GOOG");
        stocks.add("GOOG");
        return ResponseEntity.ok(stocks);
    }
}
