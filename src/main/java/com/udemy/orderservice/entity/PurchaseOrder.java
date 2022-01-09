package com.udemy.orderservice.entity;

import com.udemy.orderservice.dto.*;
import javax.persistence.*;
import lombok.*;

@Data
@Entity
@ToString
public class PurchaseOrder {

  @Id
  @GeneratedValue
  private Integer id;
  private String productId;
  private Integer userId;
  private Integer amount;
  private OrderStatus status;

}
