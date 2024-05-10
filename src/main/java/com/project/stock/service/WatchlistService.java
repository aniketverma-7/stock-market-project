package com.project.stock.service;

import com.project.stock.dto.stocks.watchlist.WatchlistList;
import com.project.stock.dto.stocks.watchlist.WatchlistRequest;
import com.project.stock.dto.stocks.watchlist.WatchlistResponse;
import com.project.stock.exception.GlobalExceptionHandler;

public interface WatchlistService {

    WatchlistList readByUserID(String email) throws GlobalExceptionHandler;
    WatchlistResponse addStockToWatchList(WatchlistRequest watchlistRequest) throws GlobalExceptionHandler;
    Boolean removeStockFromWatchList(WatchlistRequest watchlistRequest) throws GlobalExceptionHandler;
}
