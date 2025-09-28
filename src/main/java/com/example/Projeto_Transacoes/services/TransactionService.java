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
        log.info("Iniciando gravação de yna transação");
        if(transaction.value() <= 0 || transaction.dateTime().isAfter(OffsetDateTime.now())) throw new TransactionErrorException();
        listItems.add(transaction);
        log.info("Transação gravada com sucesso");
    }

    public void deleteAllTransactions(){
        log.info("Iniciando Deleção de uma transação");
        listItems.clear();
        log.info("Transação deletada com sucesso");
    }

    public List<TransactionDto> listTransactions(Integer time){
        log.info("Iniciando busca de transações em um certo periodo de tempo");
        OffsetDateTime interval = OffsetDateTime.now().minusSeconds(time);
        log.info("Retornando lista de transações em um certo periodo de tempo");
        return  listItems.stream().filter(transaction->transaction.dateTime().isAfter(interval)).toList();
    }

}
