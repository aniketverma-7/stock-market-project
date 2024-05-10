package com.project.stock.repository;

import com.project.stock.model.stocks.Stock;
import com.project.stock.model.stocks.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface StockRepository extends JpaRepository<Stock, Long> {
    Optional<Stock> findBySymbol(String symbol);
    Optional<Stock> findByStockId(Long stockID);
}
