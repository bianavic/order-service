package com.udemy.orderservice.entity;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;

// not use @Entity because we are not including JPA
@Data
@ToString
public class User {

  @Id
  private Integer id;
  private String name;
  private Integer balance;

}
