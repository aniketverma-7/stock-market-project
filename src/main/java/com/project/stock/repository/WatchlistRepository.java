package com.project.stock.repository;

import com.project.stock.model.stocks.Watchlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WatchlistRepository extends JpaRepository<Watchlist, Integer> {
}
