package com.udemy.orderservice.controller;

import com.udemy.orderservice.dto.*;
import com.udemy.orderservice.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.*;

@RestController
@RequestMapping("order")
public class PurchaseOrderController {

  @Autowired
  private OrderFulfillmentService orderFulfillmentService;

  @Autowired
  private OrderQueryService queryService;

  @PostMapping
  public Mono<ResponseEntity<PurchaseOrderResponseDTO>> order(@RequestBody Mono<PurchaseOrderRequestDTO> requestDTOMono) {
    return this.orderFulfillmentService.processOrder(requestDTOMono)
        .map(ResponseEntity::ok)
        .onErrorReturn(WebClientResponseException.class, ResponseEntity.badRequest().build()) // webclient response request
        .onErrorReturn(WebClientRequestException.class, ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).build()); // not able to connect to other services
  }

  // expose endpoint to get list of orders for a particular user
  @GetMapping("user/{userId}")
  public Flux<PurchaseOrderResponseDTO> getOrderByUserId(@PathVariable int userId) {
    return this.queryService.getProductsByUserId(userId);
  }

}
