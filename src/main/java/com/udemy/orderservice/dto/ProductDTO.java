package com.udemy.orderservice.dto;

import lombok.*;
import org.springframework.data.annotation.*;

@Data
@ToString
@NoArgsConstructor
public class ProductDTO {

  @Id
  private String id;
  private String description;
  private Integer price;

  public ProductDTO(String description, Integer price) {
    this.description = description;
    this.price = price;
  }

}
