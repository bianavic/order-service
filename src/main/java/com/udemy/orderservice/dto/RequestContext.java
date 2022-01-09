package com.udemy.orderservice.dto;

import lombok.*;

@Data
@ToString
public class RequestContext {

  private PurchaseOrderRequestDTO purchaseOrderRequestDTO;
  private ProductDTO productDTO;
  private TransactionRequestDTO transactionRequestDTO;
  private TransactionResponseDTO transactionResponseDTO;

  public RequestContext(PurchaseOrderRequestDTO purchaseOrderRequestDTO) {
    this.purchaseOrderRequestDTO = purchaseOrderRequestDTO;
  }

}
