package com.example.Projeto_Transacoes.controllers;

import com.example.Projeto_Transacoes.dtos.StatisticsDto;
import com.example.Projeto_Transacoes.services.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/statistics")
public class StatisticController {

    @Autowired
    private StatisticService statisticService;

    @GetMapping
    public ResponseEntity<StatisticsDto> getStatistics(@RequestParam(required = false,defaultValue = "60") Integer interval){
        StatisticsDto statisticsDto = statisticService.getAllStatistics(interval);
        return ResponseEntity.ok().body(statisticsDto);
    }

}
