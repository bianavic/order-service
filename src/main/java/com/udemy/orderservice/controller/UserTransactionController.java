package com.udemy.orderservice.controller;

import com.udemy.orderservice.dto.TransactionRequestDTO;
import com.udemy.orderservice.dto.TransactionResponseDTO;
import com.udemy.orderservice.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user/transaction")
public class UserTransactionController {

  @Autowired
  private TransactionService transactionService;

  @PostMapping
  public Mono<TransactionResponseDTO> createTransaction(@RequestBody Mono<TransactionRequestDTO> requestDTOMono) {
    return requestDTOMono.flatMap(this.transactionService::createTransaction);
  }

}
