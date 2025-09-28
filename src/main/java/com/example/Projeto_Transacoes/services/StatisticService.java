package com.example.Projeto_Transacoes.services;

import com.example.Projeto_Transacoes.dtos.StatisticsDto;
import com.example.Projeto_Transacoes.dtos.TransactionDto;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class StatisticService {

    private static final Logger log = LoggerFactory.getLogger(StatisticService.class);
    @Autowired
    private TransactionService transactionService;

    public StatisticsDto getAllStatistics(Integer timeInterval){
        log.info("Iniciando busca de estatisticas de trasações");
        List<TransactionDto> listTransactions= transactionService.listTransactions(timeInterval);
        if (listTransactions.isEmpty()) return new StatisticsDto(0L,0.0,0.0,0.0,0.0);
        log.info("Lista de transações no periodo correto retornada");
        DoubleSummaryStatistics doubleSummaryStatistics = listTransactions.stream().collect(Collectors.summarizingDouble(TransactionDto::value));
        log.info("Retornando estatisticas de transações");
        return new StatisticsDto(doubleSummaryStatistics.getCount(),doubleSummaryStatistics.getSum(),doubleSummaryStatistics.getAverage() ,doubleSummaryStatistics.getMin(), doubleSummaryStatistics.getMax());
    }

}
