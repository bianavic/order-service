package com.udemy.orderservice.client;

import com.udemy.orderservice.dto.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.*;

@Service
public class UserClient {

  private final WebClient webClient;

  // build webclient to detect amount
  public UserClient(@Value("${user.service.url}") String url) {
    this.webClient = WebClient.builder()
        .baseUrl(url)
        .build();
  }

  // we create a method to send transactionRequestDTO and get transactionResponseDTO from user
  // service to get the amount to authZ or not tx
  public Mono<TransactionResponseDTO> authorizeTransaction(TransactionRequestDTO requestDTO) {
    return this.webClient
        .post()
        .uri("transaction")
        .bodyValue(requestDTO)
        .retrieve()
        .bodyToMono(TransactionResponseDTO.class);
  }

}
