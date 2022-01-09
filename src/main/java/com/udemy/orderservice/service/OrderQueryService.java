package com.udemy.orderservice.service;

import com.udemy.orderservice.dto.*;
import com.udemy.orderservice.repository.*;
import com.udemy.orderservice.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import reactor.core.publisher.*;
import reactor.core.scheduler.*;

@Service
public class OrderQueryService {

  @Autowired
  private PurchaseOrderRepository orderRepository;

  // once subscribed the findByUserId will be executed
  public Flux<PurchaseOrderResponseDTO> getProductsByUserId(int userId) {
//    List<PurchaseOrder> purchaseOrders = this.orderRepository.findByUserId(userId); // BLOCKING operation, so need lazy to do things
    return Flux.fromStream(() -> this.orderRepository.findByUserId(userId).stream()) // BLOCKING, its JPA. Its time consuming
        .map(EntityDTOUtil::getPurchaseOrderResponseDTO)
        .subscribeOn(Schedulers.boundedElastic()); // because of the blocking operation
  }

}
