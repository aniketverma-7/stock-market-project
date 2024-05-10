package com.project.stock.dto.stocks.transaction;

import com.project.stock.dto.stocks.StockDTO;
import com.project.stock.enums.TransactionType;
import com.project.stock.model.Transaction;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class TransactionDTO {
    private StockDTO stockDTO;
    private Integer quantity;
    private TransactionType transactionType;
    private Double price;
    private Timestamp timestamp;
}
