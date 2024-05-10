package com.project.stock.controller.portfolio;

import com.project.stock.dto.GenericResponse;
import com.project.stock.dto.stocks.portfolio.StockCall;
import com.project.stock.exception.GlobalExceptionHandler;
import com.project.stock.service.impl.PortfolioServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/user/portfolio")
public class PortfolioController {

    @Autowired
    private PortfolioServiceImpl portfolioService;

    @PostMapping
    public ResponseEntity<?> getPortfolio(@RequestBody Map<String, String> userEmail) throws GlobalExceptionHandler {
        return ResponseEntity.ok(portfolioService.getAllByUser(userEmail.get("email")));
    }

    @PostMapping("/buy")
    public ResponseEntity<?> buyStock(@RequestBody StockCall buy) throws GlobalExceptionHandler {
        Boolean response = portfolioService.buyStock(buy);
        if (response) return ResponseEntity.ok(new GenericResponse(HttpStatus.CREATED.value(), "Bought"));
        return ResponseEntity.ok(new GenericResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Operation Unsuccessful"));
    }

    @PostMapping("/sell")
    public ResponseEntity<?> sellStock(@RequestBody StockCall sell) throws GlobalExceptionHandler {
        Boolean response = portfolioService.sellStock(sell);
        if (response) return ResponseEntity.ok(new GenericResponse(HttpStatus.CREATED.value(), "sold"));
        return ResponseEntity.ok(new GenericResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Operation Unsuccessful"));
    }

}
