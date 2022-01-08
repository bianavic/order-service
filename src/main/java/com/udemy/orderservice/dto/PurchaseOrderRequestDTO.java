package com.udemy.orderservice.dto;

import lombok.*;

@Data
@ToString
public class PurchaseOrderRequestDTO {

  private Integer userId;
  private String productId;

}
