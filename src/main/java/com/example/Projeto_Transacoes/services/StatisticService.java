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

@Service
@Slf4j
public class StatisticService {

    private static final Logger log = LoggerFactory.getLogger(StatisticService.class);
    @Autowired
    private TransactionService transactionService;

    public StatisticsDto getAllStatistics(Integer timeInterval){
        log.info("Starting the search for transactions");
        List<TransactionDto> listTransactions= transactionService.listTransactions(timeInterval);
        if (listTransactions.isEmpty()) return new StatisticsDto(0L,0.0,0.0,0.0,0.0);
        log.info("List of transactions in a certain period of time returned");
        DoubleSummaryStatistics doubleSummaryStatistics = listTransactions.stream().mapToDouble(TransactionDto::value).summaryStatistics();
        log.info("Returning the list of statistics");
        return new StatisticsDto(doubleSummaryStatistics.getCount(),doubleSummaryStatistics.getSum(),doubleSummaryStatistics.getAverage() ,doubleSummaryStatistics.getMin(), doubleSummaryStatistics.getMax());
    }

}
