package com.example.Projeto_Transacoes.services;

import com.example.Projeto_Transacoes.dtos.StatisticsDto;
import com.example.Projeto_Transacoes.dtos.TransactionDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StatisticService {

    @Autowired
    private TransactionService transactionService;

    public StatisticsDto getAllStatistics(Integer timeInterval){
        List<TransactionDto> listTransactions= transactionService.listTransactions(timeInterval);
        DoubleSummaryStatistics doubleSummaryStatistics = listTransactions.stream().collect(Collectors.summarizingDouble(TransactionDto::value));
        return new StatisticsDto(doubleSummaryStatistics.getCount(),doubleSummaryStatistics.getSum(),doubleSummaryStatistics.getAverage() ,doubleSummaryStatistics.getMin(), doubleSummaryStatistics.getMax());
    }

}
