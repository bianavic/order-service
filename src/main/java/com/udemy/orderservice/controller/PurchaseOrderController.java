package com.udemy.orderservice.controller;

import com.udemy.orderservice.dto.*;
import com.udemy.orderservice.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.*;

@RestController
@RequestMapping("order")
public class PurchaseOrderController {

  @Autowired
  private OrderFulfillmentService orderFulfillmentService;

  @Autowired
  private OrderQueryService queryService;

  @PostMapping
  public Mono<PurchaseOrderResponseDTO> order(@RequestBody Mono<PurchaseOrderRequestDTO> requestDTOMono) {
    return this.orderFulfillmentService.processOrder(requestDTOMono);
  }

  // expose endpoint to get list of orders for a particular user
  @GetMapping("user/{userId}")
  public Flux<PurchaseOrderResponseDTO> getOrderByUserId(@PathVariable int userId) {
    return this.queryService.getProductsByUserId(userId);
  }

}
