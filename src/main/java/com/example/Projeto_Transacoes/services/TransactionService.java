package com.example.Projeto_Transacoes.services;

import com.example.Projeto_Transacoes.dtos.TransactionDto;
import com.example.Projeto_Transacoes.infra.TransactionErrorException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);
    private final List<TransactionDto> listItems = new ArrayList<>();


    public void createTransaction(TransactionDto transaction){
        log.info("Starting the transaction creation process");
        if(transaction.value() <= 0 ) {
            log.error("Value can't be negative");
            throw new TransactionErrorException("Value can't be negative");
        }
        if(transaction.dateTime().isAfter(OffsetDateTime.now())) {
            log.error("Date can't be in the future");
            throw new TransactionErrorException("Date can't be in the future");
        }
        listItems.add(transaction);
        log.info("Transaction saved successfully");
    }

    public void deleteAllTransactions(){
        log.info("Starting the deleting transaction process");
        listItems.clear();
        log.info("Transaction deleted with success");
    }

    public List<TransactionDto> listTransactions(Integer time){
        log.info("Starting the searching for a transaction");
        OffsetDateTime interval = OffsetDateTime.now().minusSeconds(time);
        log.info("Returning the list of transactions in a certain period of time");
        return  listItems.stream().filter(transaction->transaction.dateTime().isAfter(interval)).toList();
    }

}
