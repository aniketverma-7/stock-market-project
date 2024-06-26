package com.project.stock.model.stocks;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "watchlist")
public class Watchlist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "watchlist_id")
    private Long watchlistId;

    @Column(name = "user_id")
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private Long userId;

    @Column(name = "stock_id")
    @JoinColumn(name = "stock_id", referencedColumnName = "stock_id")
    private Long stockId;

    public Watchlist() {
    }

    public Watchlist(Long userId, Long stockId) {
        this.userId = userId;
        this.stockId = stockId;
    }

    public Watchlist(Long watchlistId, Long userId, Long stockId) {
        this.watchlistId = watchlistId;
        this.userId = userId;
        this.stockId = stockId;
    }
}
