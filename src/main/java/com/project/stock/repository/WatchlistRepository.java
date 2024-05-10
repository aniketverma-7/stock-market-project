package com.project.stock.repository;

import com.project.stock.model.stocks.Watchlist;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WatchlistRepository extends JpaRepository<Watchlist, Long> {

    Optional<Watchlist> findByStockId(Long stockId);
    Optional<List<Watchlist>> findAllByUserId(Long userId);
}
