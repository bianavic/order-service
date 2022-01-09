package com.udemy.orderservice.util;

import com.udemy.orderservice.dto.*;
import com.udemy.orderservice.entity.*;
import org.springframework.beans.*;

// helper class to build tx request DTO
public class EntityDTOUtil {

  // build object, stored the reference in request context itself
  public static void setTransactionRequestDTO(RequestContext requestContext) {
    TransactionRequestDTO dto = new TransactionRequestDTO();
    dto.setUserId(requestContext.getPurchaseOrderRequestDTO().getUserId());
    dto.setAmount(requestContext.getProductDTO().getPrice());
    requestContext.setTransactionRequestDTO(dto);
  }

  public static PurchaseOrder getPurchaseOrder(RequestContext requestContext) {
    PurchaseOrder purchaseOrder = new PurchaseOrder();
    purchaseOrder.setUserId(requestContext.getPurchaseOrderRequestDTO().getUserId());
    purchaseOrder.setProductId(requestContext.getPurchaseOrderRequestDTO().getProductId());
    purchaseOrder.setAmount(requestContext.getProductDTO().getPrice());

    TransactionStatus status = requestContext.getTransactionResponseDTO().getStatus();
    OrderStatus orderStatus = TransactionStatus.APPROVED.equals(status) ? OrderStatus.COMPLETED : OrderStatus.FAILED;

    purchaseOrder.setStatus(orderStatus); // the status we get from the tx request dto

    return purchaseOrder;
  }

  public static PurchaseOrderResponseDTO getPurchaseOrderResponseDTO(PurchaseOrder purchaseOrder) {
    PurchaseOrderResponseDTO dto = new PurchaseOrderResponseDTO();
    BeanUtils.copyProperties(purchaseOrder, dto);
    dto.setOrderId(purchaseOrder.getId());
    return dto;
  }

}
