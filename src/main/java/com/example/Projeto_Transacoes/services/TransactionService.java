package com.example.Projeto_Transacoes.services;

import com.example.Projeto_Transacoes.dtos.TransactionDto;
import com.example.Projeto_Transacoes.infra.TransactionErrorException;
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
        if(transaction.value() <= 0 || transaction.dateTime().isAfter(OffsetDateTime.now())) throw new TransactionErrorException();
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
