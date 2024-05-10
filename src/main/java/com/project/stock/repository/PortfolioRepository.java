package com.project.stock.repository;

import com.project.stock.model.stocks.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {
    Portfolio findByStockIdAndUserId(Long stockId, Long userId);
    List<Portfolio> findByUserId(Long userId);
}
