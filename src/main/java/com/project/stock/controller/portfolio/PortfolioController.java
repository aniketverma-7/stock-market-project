package com.project.stock.controller.portfolio;

import com.project.stock.dto.stocks.portfolio.BuyStock;
import com.project.stock.exception.GlobalExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user/portfolio")
public class PortfolioController {


    @PostMapping
    public ResponseEntity<?> getPortfolio(@RequestBody Map<String,String> userEmail) throws GlobalExceptionHandler {
        return null;
    }

    @PostMapping("/buy")
    public ResponseEntity<?> buyStock(@RequestBody BuyStock stock) throws GlobalExceptionHandler {
        System.out.println(stock.toString());
        return null;
    }

    @PostMapping("/sell")
    public ResponseEntity<?> sellStock(@RequestBody Map<String,String> userEmail) throws GlobalExceptionHandler {
        return null;
    }

}
