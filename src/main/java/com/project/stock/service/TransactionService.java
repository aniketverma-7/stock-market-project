package com.project.stock.service;

import com.project.stock.dto.stocks.transaction.TransactionDTO;
import com.project.stock.model.Transaction;
import com.project.stock.service.impl.TransactionServiceImpl;

import java.util.List;

public interface TransactionService {

    public void logTransaction(Transaction transaction);

    List<TransactionDTO> getAllTransactionByUser(String email);
}
