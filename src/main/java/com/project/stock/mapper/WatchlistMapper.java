package com.project.stock.mapper;

import com.project.stock.dto.stocks.StockDTO;
import com.project.stock.dto.stocks.WatchlistDTO;
import com.project.stock.model.stocks.Stock;
import com.project.stock.model.stocks.Watchlist;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface WatchlistMapper {
    WatchlistDTO mapEntityToResponse(Watchlist watchlist);

    Watchlist mapRequestToEntity(WatchlistDTO watchlistDTO);
}
