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
    private Long portfolioId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "stock_id")
    private Long stockId;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "avg_purchase_price")
    private Double avgPurchasePrice;
}
