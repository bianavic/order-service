package com.udemy.orderservice.dto;

import lombok.*;

// post request for a transaction
@Data
@ToString
public class TransactionRequestDTO {

  // what will be send
  private Integer userId;
  private Integer amount;

  public TransactionRequestDTO(Integer userId, Integer amount) {
    this.userId = userId;
    this.amount = amount;
  }

  public TransactionRequestDTO() {}

}
