package com.udemy.orderservice.service;

import com.udemy.orderservice.dto.TransactionRequestDTO;
import com.udemy.orderservice.dto.TransactionResponseDTO;
import com.udemy.orderservice.dto.TransactionStatus;
import com.udemy.orderservice.repository.UserRepository;
import com.udemy.orderservice.repository.UserTransactionRepository;
import com.udemy.orderservice.utils.EntityToDTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TransactionService {

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserTransactionRepository userTransactionRepository;

  public Mono<TransactionResponseDTO> createTransaction(final TransactionRequestDTO requestDTO) {
    return this.userRepository.updateUserBalance(requestDTO.getUserId(), requestDTO.getAmount())
        .filter(Boolean::booleanValue)
        .map(b -> EntityToDTOUtil.toEntity(requestDTO))
        .flatMap(this.userTransactionRepository::save)
        .map(ut -> EntityToDTOUtil.toDTO(requestDTO, TransactionStatus.APPROVED))
        .defaultIfEmpty(EntityToDTOUtil.toDTO(requestDTO, TransactionStatus.DECLINED));
  }

}
