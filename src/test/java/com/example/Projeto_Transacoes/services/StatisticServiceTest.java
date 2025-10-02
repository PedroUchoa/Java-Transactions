package com.example.Projeto_Transacoes.services;

import com.example.Projeto_Transacoes.dtos.StatisticsDto;
import com.example.Projeto_Transacoes.dtos.TransactionDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.OffsetDateTime;
import java.util.Collections;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class StatisticServiceTest {

    @InjectMocks
    private StatisticService statisticService;

    @Mock
    private TransactionService transactionService;

    private TransactionDto transaction;
    private StatisticsDto statistics;

    @BeforeEach
    void setUp(){
        transaction = new TransactionDto(20.0, OffsetDateTime.now());
        statistics = new StatisticsDto(1L,20.0,20.,20.0,20.0);
    }

    @Test
    @DisplayName("Deve retornar todas as estatisticas")
    void getAllStatistics(){
        when(transactionService.listTransactions(60)).thenReturn(Collections.singletonList(transaction));
        StatisticsDto statisticsDto = statisticService.getAllStatistics(60);
        verify(transactionService,times(1)).listTransactions(60);
        Assertions.assertThat(statisticsDto).usingRecursiveComparison().isEqualTo(statistics);
    }

    @Test
    @DisplayName("Deve retornar estatistica para uma lista vazia")
    void getAllStatisticsEmptyList(){
        StatisticsDto emptyStatistics = new StatisticsDto(0L,0.0,0.0,0.0,0.0);

        when(transactionService.listTransactions(60)).thenReturn(Collections.emptyList());

        StatisticsDto statisticsDto = statisticService.getAllStatistics(60);

        verify(transactionService,times(1)).listTransactions(60);
        Assertions.assertThat(statisticsDto).usingRecursiveComparison().isEqualTo(emptyStatistics);
    }

}
