package com.example.Projeto_Transacoes.controllers;

import com.example.Projeto_Transacoes.dtos.StatisticsDto;
import com.example.Projeto_Transacoes.services.StatisticService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/statistics")
@Slf4j
public class StatisticController {

    private static final Logger log = LoggerFactory.getLogger(StatisticController.class);
    @Autowired
    private StatisticService statisticService;


    @GetMapping
    public ResponseEntity<StatisticsDto> getStatistics(@RequestParam(required = false,defaultValue = "60") Integer interval){

        StatisticsDto statisticsDto = statisticService.getAllStatistics(interval);
        return ResponseEntity.ok().body(statisticsDto);
    }

}
