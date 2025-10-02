package com.example.Projeto_Transacoes.services;

import com.example.Projeto_Transacoes.dtos.TransactionDto;
import com.example.Projeto_Transacoes.infra.TransactionErrorException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @InjectMocks
    private TransactionService transactionService;

    private TransactionDto transaction;

    @BeforeEach
    void setUp(){
        transaction = new TransactionDto(20.0,OffsetDateTime.now());
    }

    @Test
    @DisplayName("Deve Criar uma transação com sucesso")
    void createTransaction(){
        transactionService.createTransaction(this.transaction);
        List<TransactionDto> transactionDtos = transactionService.listTransactions(50000);
        assertTrue(transactionDtos.contains(this.transaction));
    }

    @Test
    @DisplayName("Deve Jogar uma exception quando salvar uma transação com valor negativo")
    void createTransactionErrorValue(){
        TransactionErrorException excep = assertThrows(TransactionErrorException.class,()->transactionService.createTransaction(new TransactionDto(-20.0,OffsetDateTime.now())));
        assertEquals("Value can't be negative",excep.getMessage());
    }

    @Test
    @DisplayName("Deve Jogar uma exception quando salvar uma transação com data futura")
    void createTransactionErrorDate(){
        TransactionErrorException excep = assertThrows(TransactionErrorException.class,()->transactionService.createTransaction(new TransactionDto(20.0,OffsetDateTime.now().plusHours(10))));
        assertEquals("Date can't be in the future",excep.getMessage());
    }

    @Test
    @DisplayName("Deve deletar todas as transações")
    void deleteTransactions(){
        transactionService.createTransaction(this.transaction);
        transactionService.deleteAllTransactions();
        List<TransactionDto> transactionDtos = transactionService.listTransactions(50000);
        assertTrue(transactionDtos.isEmpty());
    }


}

