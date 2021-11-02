package com.udemy.orderservice.controller;

import com.udemy.orderservice.dto.UserDTO;
import com.udemy.orderservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("user")
public class UserController {

  @Autowired
  private UserService userService;

  @GetMapping("all")
  public Flux<UserDTO> all() {
    return this.userService.all();
  }

  @GetMapping("{id}")
  public Mono<ResponseEntity<UserDTO>> byId(@PathVariable int id) {
    return this.userService.getUserById(id)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Mono<UserDTO> saveUser(@RequestBody Mono<UserDTO> userDTOMono) {
    return this.userService.saveUser(userDTOMono);
  }

  @PutMapping("{id}")
  public Mono<ResponseEntity<UserDTO>> updateUser(@PathVariable int id, Mono<UserDTO> userDTOMono) {
    return this.userService.updateUser(id, userDTOMono)
        .map(ResponseEntity::ok)
        .defaultIfEmpty(ResponseEntity.notFound().build());
  }

  @DeleteMapping("{id}")
  public Mono<Void> deleteUser(@PathVariable int id) {
    return this.userService.deleteById(id);
  }

}
