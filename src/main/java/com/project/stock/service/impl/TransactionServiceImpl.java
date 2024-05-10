package com.project.stock.service.impl;

import com.project.stock.dto.stocks.StockDTO;
import com.project.stock.dto.stocks.transaction.TransactionDTO;
import com.project.stock.mapper.StockMapper;
import com.project.stock.model.Transaction;
import com.project.stock.model.User;
import com.project.stock.repository.StockRepository;
import com.project.stock.repository.TransactionRepository;
import com.project.stock.repository.UserRepository;
import com.project.stock.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private StockMapper stockMapper;

    @Override
    public void logTransaction(Transaction transaction) {
        repository.save(transaction);
    }

    @Override
    public List<TransactionDTO> getAllTransactionByUser(String email) {
        System.out.println(email);
        User user = userRepository.findByEmail(email).get();
        List<Transaction> transactions = repository.findAllByUserId(Long.valueOf(user.getId()));
        TransactionDTO transactionDTO = new TransactionDTO();

        List<TransactionDTO> responseTransactions = new ArrayList<>();

        for (Transaction transaction:transactions){
            StockDTO stockDTO = stockMapper.mapEntityToResponse(stockRepository.findByStockId(transaction.getStockId()).get());
            TransactionDTO transactionResponse = new TransactionDTO();
            transactionResponse.setStockDTO(stockDTO);
            transactionResponse.setQuantity(transaction.getQuantity());
            transactionResponse.setTransactionType(transaction.getTransactionType());
            transactionResponse.setPrice(transaction.getPrice());
            transactionResponse.setTimestamp(transaction.getTimestamp());

            responseTransactions.add(transactionResponse);
        }

        return responseTransactions;
    }
}
