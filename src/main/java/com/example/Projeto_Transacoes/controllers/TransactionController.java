package com.example.Projeto_Transacoes.controllers;

import com.example.Projeto_Transacoes.dtos.TransactionDto;
import com.example.Projeto_Transacoes.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;


    @PostMapping
    public ResponseEntity<Void> createTransaction(@RequestBody TransactionDto transaction){
        transactionService.createTransaction(transaction);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteAllTransactions(){
        transactionService.deleteAllTransactions();
        return ResponseEntity.ok().build();
    }

}
