package com.udemy.orderservice.entity;

import java.time.LocalDateTime;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Data
@ToString
public class UserTransaction {

  @Id
  private Integer id;
  private Integer userId; // foreign key
  private Integer amount; // detect from user table
  private LocalDateTime transactionDate;

}
