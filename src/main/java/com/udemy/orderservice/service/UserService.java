package com.udemy.orderservice.service;

import com.udemy.orderservice.dto.UserDTO;
import com.udemy.orderservice.repository.UserRepository;
import com.udemy.orderservice.utils.EntityToDTOUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  public Flux<UserDTO> all() {
    return this.userRepository.findAll()
        .map(EntityToDTOUtil::toDto);
  }

  public Mono<UserDTO> getUserById(final int userId) {
    return this.userRepository.findById(userId)
        .map(EntityToDTOUtil::toDto);
  }

  public Mono<UserDTO> saveUser(Mono<UserDTO> userDTOMono) {
    return userDTOMono
        .map(EntityToDTOUtil::toEntity)
        .flatMap(this.userRepository::save)
        .map(EntityToDTOUtil::toDto);
  }

  public Mono<UserDTO> updateUser(int id, Mono<UserDTO> userDTOMono) {
    return this.userRepository.findById(id)
        .flatMap(u -> userDTOMono
            .map(EntityToDTOUtil::toEntity)
            .doOnNext(e -> e.setId(id)))
        .flatMap(this.userRepository::save)
        .map(EntityToDTOUtil::toDto);
  }

  public Mono<Void> deleteById(int id) {
    return this.userRepository.deleteById(id);
  }

}
