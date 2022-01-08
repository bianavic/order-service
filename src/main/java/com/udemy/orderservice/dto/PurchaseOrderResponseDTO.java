package com.udemy.orderservice.dto;

import lombok.*;

@Data
@ToString
public class PurchaseOrderResponseDTO {

  private Integer orderId;
  private Integer userId;
  private String productId;
  private Integer amount;
  private OrderStatus status;

}
