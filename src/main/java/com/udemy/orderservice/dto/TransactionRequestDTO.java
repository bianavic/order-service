package com.udemy.orderservice.dto;

import lombok.Data;
import lombok.ToString;

// post request for a transaction
@Data
@ToString
public class TransactionRequestDTO {

  // what will be send
  private Integer userId;
  private Integer amount;

}
