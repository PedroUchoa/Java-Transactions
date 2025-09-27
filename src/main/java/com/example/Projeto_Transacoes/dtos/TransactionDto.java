package com.example.Projeto_Transacoes.dtos;

import java.time.OffsetDateTime;

public record TransactionDto(Double value, OffsetDateTime dateTime) {
}
