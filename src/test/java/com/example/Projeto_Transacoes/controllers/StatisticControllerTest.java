package com.example.Projeto_Transacoes.controllers;


import com.example.Projeto_Transacoes.dtos.StatisticsDto;
import com.example.Projeto_Transacoes.dtos.TransactionDto;
import com.example.Projeto_Transacoes.services.StatisticService;
import com.example.Projeto_Transacoes.services.TransactionService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class StatisticControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private StatisticService statisticService;

    private TransactionDto transaction;
    private StatisticsDto statistics;

    @BeforeEach
    void setUp(){
        transaction = new TransactionDto(20.0, OffsetDateTime.now());
        statistics = new StatisticsDto(1L,20.0,20.,20.0,20.0);
    }

    @Test
    @DisplayName("Deve Retornar Status 200 Ok ao buscar estatisticas")
    void getStatistics() throws Exception {
        when(statisticService.getAllStatistics(60)).thenReturn(statistics);

        mockMvc.perform(MockMvcRequestBuilders.get("/statistics")
                .param("interval","60")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.count").value(1L))
                .andExpect(jsonPath("$.sum").value(20.0))
                .andExpect(jsonPath("$.avg").value(20.0))
                .andExpect(jsonPath("$.min").value(20.0))
                .andExpect(jsonPath("$.max").value(20.0));

        verify(statisticService).getAllStatistics(60);
    }

    @Test
    @DisplayName("Deve Retornar Status 200 Ok ao buscar estatisticas")
    void getStatisticsZero() throws Exception {

        StatisticsDto statisticsEmpty = new StatisticsDto(0L,0.0,0.0,0.0,0.0);

        when(statisticService.getAllStatistics(60)).thenReturn(statisticsEmpty);

        mockMvc.perform(MockMvcRequestBuilders.get("/statistics")
                .param("interval","60")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$.count").value(0L))
                .andExpect(jsonPath("$.sum").value(0.0))
                .andExpect(jsonPath("$.avg").value(0.0))
                .andExpect(jsonPath("$.min").value(0.0))
                .andExpect(jsonPath("$.max").value(0.0));



        verify(statisticService).getAllStatistics(60);

    }



}
