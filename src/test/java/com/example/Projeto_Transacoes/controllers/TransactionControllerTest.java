package com.example.Projeto_Transacoes.controllers;

import com.example.Projeto_Transacoes.dtos.TransactionDto;
import com.example.Projeto_Transacoes.infra.TransactionErrorException;
import com.example.Projeto_Transacoes.services.TransactionService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.assertj.MockMvcTester;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
public class TransactionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private TransactionService transactionService;

    private TransactionDto transaction;

    @BeforeEach
    void setUp(){
        transaction = new TransactionDto(20.0, OffsetDateTime.of(2025,10,2,17,43,0,0, ZoneOffset.UTC));
    }

    @Test
    @DisplayName("Deve retornar status 200 Created ao criar uma transação")
    void createTransaction() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/transaction")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(this.transaction)))
                .andExpect(MockMvcResultMatchers.status().isCreated());

        ArgumentCaptor<TransactionDto> userCaptor = ArgumentCaptor.forClass(TransactionDto.class);
        verify(transactionService,times(1)).createTransaction(userCaptor.capture());
        assertEquals(userCaptor.getValue().dateTime(),transaction.dateTime());
    }

    @Test
    @DisplayName("Deve retornar status 422 Unprocessable Entity ao criar uma transação com valor invalido")
    void createdTransactionValueError() throws Exception{

        doThrow(new TransactionErrorException("error")).when(transactionService).createTransaction(transaction);

        mockMvc.perform(MockMvcRequestBuilders.post("/transaction")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(transaction)))
                .andExpect(MockMvcResultMatchers.status().isUnprocessableEntity());
    }

    @Test
    @DisplayName("Deve retornar Status 200 OK ao deletar todas as transação com sucesso")
    void deleteTransaction() throws Exception {
        doNothing().when(transactionService).deleteAllTransactions();

        mockMvc.perform(MockMvcRequestBuilders.delete("/transaction")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        verify(transactionService).deleteAllTransactions();
    }


}
