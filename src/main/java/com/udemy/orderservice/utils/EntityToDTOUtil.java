package com.udemy.orderservice.utils;

import com.udemy.orderservice.dto.TransactionRequestDTO;
import com.udemy.orderservice.dto.TransactionResponseDTO;
import com.udemy.orderservice.dto.TransactionStatus;
import com.udemy.orderservice.dto.UserDTO;
import com.udemy.orderservice.entity.User;
import com.udemy.orderservice.entity.UserTransaction;
import java.time.LocalDateTime;
import javax.print.attribute.standard.OrientationRequested;
import org.springframework.beans.BeanUtils;

public class EntityToDTOUtil {

  public static UserDTO toDto(User user) {
    UserDTO dto = new UserDTO();
    BeanUtils.copyProperties(user, dto);
    return dto;
  }

  public static User toEntity(UserDTO dto) {
    User user = new User();
    BeanUtils.copyProperties(dto, user);
    return user;
  }

  // request
  public static UserTransaction toEntity(TransactionRequestDTO requestDTO) {
    UserTransaction userTransaction = new UserTransaction();
    BeanUtils.copyProperties(requestDTO, userTransaction);
    userTransaction.setUserId(requestDTO.getUserId());
    userTransaction.setAmount(requestDTO.getAmount());
    userTransaction.setTransactionDate(LocalDateTime.now());
    return userTransaction;
  }

  // response
  public static TransactionResponseDTO toDTO(TransactionRequestDTO requestDTO, TransactionStatus status) {
    TransactionResponseDTO responseDTO = new TransactionResponseDTO();
    responseDTO.setUserId(requestDTO.getUserId());
    responseDTO.setAmount(requestDTO.getAmount());
    responseDTO.setStatus(status);
    return responseDTO;
  }

}
