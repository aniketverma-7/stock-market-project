package com.project.stock.dto.stocks;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WatchlistDTO {

    private Long watchlistId;
    private Long userId;
    private Long stockId;
}
