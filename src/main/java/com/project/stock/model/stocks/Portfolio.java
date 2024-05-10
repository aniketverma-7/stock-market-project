package com.project.stock.model.stocks;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "portfolio")
public class Portfolio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "portfolio_id")
    private long portfolioId;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "stock_id")
    private long stockId;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "avg_purchase_price")
    private double avgPurchasePrice;

    @Column(name = "total_investment")
    private double totalInvestment;

    @Column(name = "current_price")
    private double currentPrice;

    @Column(name = "total_value")
    private double totalValue;
}