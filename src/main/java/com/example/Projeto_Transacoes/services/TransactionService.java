package com.example.Projeto_Transacoes.services;

import com.example.Projeto_Transacoes.dtos.TransactionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final List<TransactionDto> listItems = new ArrayList<>();


    public void createTransaction(TransactionDto transaction){
        listItems.add(transaction);
    }

    public List<TransactionDto> listAllItems(){
        return listItems;
    }

    public List<TransactionDto> listTransactions(Integer time){
        OffsetDateTime interval = OffsetDateTime.now().minusSeconds(time);
        return  listItems.stream().filter(transaction->transaction.dateTime().isAfter(interval)).toList();
    }

}
