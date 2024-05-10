package com.project.stock.service.impl;

import com.project.stock.dto.stocks.portfolio.PortfolioResponse;
import com.project.stock.dto.stocks.portfolio.StockCall;
import com.project.stock.enums.TransactionType;
import com.project.stock.exception.GlobalExceptionHandler;
import com.project.stock.mapper.StockMapper;
import com.project.stock.model.Transaction;
import com.project.stock.model.User;
import com.project.stock.model.stocks.Portfolio;
import com.project.stock.model.stocks.Stock;
import com.project.stock.repository.PortfolioRepository;
import com.project.stock.repository.StockRepository;
import com.project.stock.repository.TransactionRepository;
import com.project.stock.repository.UserRepository;
import com.project.stock.service.PortfolioService;
import com.project.stock.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StockRepository stockRepository;
    @Autowired
    private PortfolioRepository portfolioRepository;
    @Autowired
    private StockServiceImpl stockService;
    @Autowired
    private StockMapper stockMapper;
    @Autowired
    private TransactionService transactionService;
    //
    @Override
    public Boolean buyStock(StockCall buy) throws GlobalExceptionHandler {
        User user = userRepository.findByEmail(buy.getEmail()).get();
        Stock stock = stockRepository.findBySymbol(buy.getSymbol()).get();
        if (user != null && stock != null) {
            Portfolio storedPortfolio = portfolioRepository.findByStockIdAndUserId(stock.getStockId(), Long.valueOf(user.getId()));
            System.out.println("Stored:"+ storedPortfolio);
            Double currentPrice = stockService.getCurrentPrice(stock.getSymbol());
            if (storedPortfolio != null) {
                Long currentQuantity = Long.valueOf(storedPortfolio.getQuantity());
                Double currentTotal = (storedPortfolio.getTotalInvestment() + (buy.getQuantity() * currentPrice));
                Double newAverage = currentTotal / (currentQuantity + buy.getQuantity());

                storedPortfolio.setAvgPurchasePrice(newAverage);
                storedPortfolio.setQuantity(storedPortfolio.getQuantity() + buy.getQuantity());
                storedPortfolio.setCurrentPrice(currentPrice);
                storedPortfolio.setTotalInvestment(currentTotal);
                storedPortfolio.setTotalValue(storedPortfolio.getQuantity() * currentPrice);
                portfolioRepository.save(storedPortfolio);
            } else {
                System.out.println(currentPrice);
                Portfolio portfolio = new Portfolio();
                portfolio.setStockId(stock.getStockId());
                portfolio.setUserId(Long.valueOf(user.getId()));
                portfolio.setQuantity(buy.getQuantity());
                portfolio.setCurrentPrice(currentPrice);
                portfolio.setAvgPurchasePrice((buy.getQuantity() * currentPrice) / buy.getQuantity());
                portfolio.setTotalInvestment(buy.getQuantity() * portfolio.getAvgPurchasePrice());
                portfolio.setTotalValue(buy.getQuantity() * portfolio.getCurrentPrice());
                portfolioRepository.save(portfolio);
            }

            Transaction transaction = new Transaction();
            transaction.setUserId(Long.valueOf(user.getId()));
            transaction.setStockId(stock.getStockId());
            transaction.setTransactionType(TransactionType.BUY);
            transaction.setQuantity(buy.getQuantity());
            transaction.setPrice(currentPrice);
            transaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
            transactionService.logTransaction(transaction);
            return true;

        }
        return false;
    }

    @Override
    public Boolean sellStock(StockCall sell) throws GlobalExceptionHandler {
        User user = userRepository.findByEmail(sell.getEmail()).get();
        Stock stock = stockRepository.findBySymbol(sell.getSymbol()).get();

        if (user != null && stock != null) {
            Portfolio storedPortfolio = portfolioRepository.findByStockIdAndUserId(stock.getStockId(), Long.valueOf(user.getId()));
            Integer currentQty = storedPortfolio.getQuantity();
            Double currentPrice = stockService.getCurrentPrice(stock.getSymbol());
            if (currentQty - sell.getQuantity() > 0) {

                int remainingQuantity = storedPortfolio.getQuantity() - sell.getQuantity();
                storedPortfolio.setQuantity(remainingQuantity);
//                double totalValueSold = sell.getQuantity() * currentPrice;
                double totalInvestmentRemaining = remainingQuantity * storedPortfolio.getAvgPurchasePrice();
                double newTotalValue = totalInvestmentRemaining + (sell.getQuantity() * currentPrice);
                storedPortfolio.setTotalValue(newTotalValue);

                portfolioRepository.save(storedPortfolio);
            } else {
                portfolioRepository.delete(storedPortfolio);
            }

            Transaction transaction = new Transaction();
            transaction.setUserId(Long.valueOf(user.getId()));
            transaction.setStockId(stock.getStockId());
            transaction.setTransactionType(TransactionType.SELL);
            transaction.setQuantity(sell.getQuantity());
            transaction.setPrice(currentPrice);
            transaction.setTimestamp(new Timestamp(System.currentTimeMillis()));
            transactionService.logTransaction(transaction);
            return true;
        }
        return false;
    }

    @Override
    public List<PortfolioResponse> getAllByUser(String email) {
        User user = userRepository.findByEmail(email).get();
        List<Portfolio> portfolio = portfolioRepository.findByUserId(Long.valueOf(user.getId()));
        List<PortfolioResponse> responses = new ArrayList<>();
        for (Portfolio portfolio1 : portfolio) {
            PortfolioResponse portfolioResponse = new PortfolioResponse();
            portfolioResponse.setQuantity(portfolio1.getQuantity());
            portfolioResponse.setTotalValue(portfolio1.getTotalValue());
            portfolioResponse.setCurrentPrice(portfolio1.getCurrentPrice());
            portfolioResponse.setAvgPurchasePrice(portfolio1.getAvgPurchasePrice());
            portfolioResponse.setTotalInvestment(portfolio1.getTotalInvestment());
            Stock stock = stockRepository.findByStockId(portfolio1.getStockId()).get();
            portfolioResponse.setStock(stockMapper.mapEntityToResponse(stock));
            responses.add(portfolioResponse);
        }
        return responses;
    }


}
