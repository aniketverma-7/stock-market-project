package com.project.stock.controller.transaction;


import com.project.stock.dto.GenericResponse;
import com.project.stock.dto.stocks.transaction.TransactionDTO;
import com.project.stock.service.TransactionService;
import com.project.stock.service.impl.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user/transactions")
public class TransactionController {

    @Autowired
    private TransactionServiceImpl service;
    @PostMapping()
    ResponseEntity<?> getAllTransactionByUser(@RequestBody Map<String,String> email){

        List<TransactionDTO> transactionDTO = service.getAllTransactionByUser(email.get("email"));
        if(transactionDTO == null)
            return ResponseEntity.ok(new GenericResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Unable to fetch at the moment."));
        return ResponseEntity.ok(transactionDTO);
    }
}
