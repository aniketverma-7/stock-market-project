package com.project.stock.controller.news;

import com.project.stock.dto.news.NewsDTO;
import com.project.stock.exception.GlobalExceptionHandler;
import com.project.stock.service.NewsAPIService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class NewsController {

    @Autowired private NewsAPIService apiService;

    @GetMapping(value = "/news") // Changed the value here
    public ResponseEntity<NewsDTO> getNews(HttpServletRequest request) throws GlobalExceptionHandler {
        NewsDTO newsDTO = apiService.getNews("");
        if(newsDTO == null)
            throw new RuntimeException("Unable to fetch news at the moment");
        return ResponseEntity.ok(newsDTO);
    }

    //ENDPOINT for searching specific stock or tickers
    @GetMapping(value = "/news",params = "tickers") // Changed the value here
    public ResponseEntity<?> getNewsByTicker(@RequestParam(name = "tickers") String ticker, HttpServletRequest request) throws GlobalExceptionHandler{
        NewsDTO newsDTO = apiService.getNews(ticker);
        if(newsDTO == null)
            throw new RuntimeException("Unable to fetch news at the moment");
        return ResponseEntity.ok(newsDTO);
    }
}
