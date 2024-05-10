package com.project.stock.service.impl;

import com.project.stock.dto.stocks.WatchlistDTO;
import com.project.stock.dto.stocks.watchlist.WatchlistList;
import com.project.stock.dto.stocks.watchlist.WatchlistRequest;
import com.project.stock.dto.stocks.watchlist.WatchlistResponse;
import com.project.stock.exception.GlobalExceptionHandler;
import com.project.stock.mapper.StockMapper;
import com.project.stock.mapper.WatchlistMapper;
import com.project.stock.model.stocks.Stock;
import com.project.stock.model.stocks.Watchlist;
import com.project.stock.repository.StockRepository;
import com.project.stock.repository.UserRepository;
import com.project.stock.repository.WatchlistRepository;
import com.project.stock.service.WatchlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WatchlistServiceImpl implements WatchlistService {

    @Autowired
    private WatchlistRepository watchlistRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WatchlistMapper watchlistMapper;

    @Override
    public WatchlistList readByUserID(String email) throws GlobalExceptionHandler {
        Long id = Long.valueOf(userRepository.findByEmail(email).get().getId());
        System.out.println(id);
        Optional<List<Watchlist>> watchlists = watchlistRepository.findAllByUserId(id);
        ArrayList<Long> stockIds = watchlists.get()
                .stream()
                .map(Watchlist::getStockId)
                .collect(Collectors.toCollection(ArrayList::new));
        List<Stock> stocks = new ArrayList<>();
        for(Long idx:stockIds)
            stocks.add(stockRepository.findByStockId(idx).get());

        return new WatchlistList(HttpStatus.OK.value(), stocks);
    }

    @Override
    public WatchlistResponse addStockToWatchList(WatchlistRequest watchlistRequest) throws GlobalExceptionHandler {
        Optional<Stock> existing = stockRepository.findBySymbol(watchlistRequest.getStockDTO().getSymbol());
        if (existing.isPresent()) {
            Optional<Watchlist> watchlist = watchlistRepository.findByStockId(existing.get().getStockId());
            if (watchlist.isPresent()) {
                return new WatchlistResponse(HttpStatus.CONFLICT.value(), watchlistRequest.getEmail(), watchlistRequest.getStockDTO());
            } else {
                if(saveToWatchlist(watchlistRequest, existing.get()) != null)
                    return new WatchlistResponse(HttpStatus.CREATED.value(), watchlistRequest.getEmail(), stockMapper.mapEntityToResponse(existing.get()));
                else return null;
            }
        } else {
            Stock stock = stockMapper.mapRequestToEntity(watchlistRequest.getStockDTO());
            stock = stockRepository.save(stock);
            if(saveToWatchlist(watchlistRequest, stock) != null)
                return new WatchlistResponse(HttpStatus.CREATED.value(), watchlistRequest.getEmail(),watchlistRequest.getStockDTO());
            else return null;
        }
    }

    private WatchlistDTO saveToWatchlist(WatchlistRequest watchlistRequest, Stock  stock){
        WatchlistDTO obj = new WatchlistDTO();
        obj.setUserId(Long.valueOf(userRepository.findByEmail(watchlistRequest.getEmail()).get().getId()));
        obj.setStockId(stock.getStockId());
        return watchlistMapper.mapEntityToResponse(watchlistRepository.save(watchlistMapper.mapRequestToEntity(obj)));
    }

    @Override
    public Boolean removeStockFromWatchList(WatchlistRequest watchlistRequest) {
        Optional<Stock> existing = stockRepository.findBySymbol(watchlistRequest.getStockDTO().getSymbol());
        Optional<Watchlist> watchlistDTO = watchlistRepository.findByStockId(existing.get().getStockId());
        System.out.println(watchlistDTO.isPresent());
        if(watchlistDTO.isPresent()) {
            watchlistRepository.delete(watchlistDTO.get());
            return true;
        }
        return false;
    }


}
