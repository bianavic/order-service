package com.udemy.orderservice.dto;

import lombok.*;

// once transaction is done (request)
// i want to send a response ( + a tx status)
@Data
@ToString
public class TransactionResponseDTO {

  private Integer userId;
  private Integer amount;
  // if user has enough balance will be approved, otherwise will be declined
  private TransactionStatus status;

}
