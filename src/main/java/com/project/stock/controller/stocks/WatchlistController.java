package com.project.stock.controller.stocks;


import com.project.stock.dto.stocks.watchlist.WatchlistRequest;
import com.project.stock.dto.stocks.watchlist.WatchlistResponse;
import com.project.stock.exception.GlobalExceptionHandler;
import com.project.stock.service.impl.WatchlistServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/user/watchlist")
public class WatchlistController {

    @Autowired
    private WatchlistServiceImpl watchlistService;

    //TODO ADD AND REMOVE FOR MUTUAL FUND
    @PostMapping("/add")
    public ResponseEntity<?> addStockToWatchList(@RequestBody WatchlistRequest watchlistRequest) throws GlobalExceptionHandler {
        WatchlistResponse watchlistResponse = watchlistService.addStockToWatchList(watchlistRequest);
        if (watchlistResponse == null) ResponseEntity.ok("already part of watchlist");
        return ResponseEntity.status(HttpStatus.OK).body(watchlistResponse);
    }

    @PostMapping("/delete")
    public ResponseEntity<?> removeStockFromWatchList(@RequestBody WatchlistRequest watchlistRequest) throws GlobalExceptionHandler {
        Boolean result = watchlistService.removeStockFromWatchList(watchlistRequest);
        System.out.println(result);
        if (!result) throw new InternalError("Internal Server Error");
        return ResponseEntity.status(HttpStatus.OK).body("Deleted");
    }

    @PostMapping()
    public ResponseEntity<?> getWatchlist(@RequestBody Map<String,String> email) throws GlobalExceptionHandler{
        return ResponseEntity.ok(watchlistService.readByUserID(email.get("email")));
    }
}
