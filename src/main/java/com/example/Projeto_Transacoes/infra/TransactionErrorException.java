package com.example.Projeto_Transacoes.infra;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY, reason = "The transaction was not accepted for any reason")
public class TransactionErrorException extends RuntimeException{

}
