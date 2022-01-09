package com.udemy.orderservice.service;

import com.udemy.orderservice.client.*;
import com.udemy.orderservice.dto.*;
import com.udemy.orderservice.dto.RequestContext;
import com.udemy.orderservice.repository.*;
import com.udemy.orderservice.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import reactor.core.publisher.*;
import reactor.core.scheduler.*;

@Service
public class OrderFulfillmentService {

  @Autowired
  private ProductCliente productCliente;

  @Autowired
  private UserClient userClient;

  @Autowired
  private PurchaseOrderRepository orderRepository;

  /**
   *
   * @param requestDTOMono
   * @return
   *
   *   Steps in sequence:
   *   1 - CALL product service to get product information
   *   2 - SEND user and the tx records to post the tx to user service
   *   3 - CREATE purchase order based on 2o response
   *   4 - POST a recording in that database
   *   5 - CREATE purchase responseDTO
   *   6 - SEND it
   *
   */
  public Mono<PurchaseOrderResponseDTO> processOrder(Mono<PurchaseOrderRequestDTO> requestDTOMono) {
    // emits purchase of requestDTO
    return requestDTOMono.map(RequestContext::new)
        .flatMap(this::productRequestResponse)
        .doOnNext(EntityDTOUtil::setTransactionRequestDTO)
        .flatMap(this::userRequestResponse)  // if amount is detected successfully we can fulfill the order AND with amount we can get product
        .map(EntityDTOUtil::getPurchaseOrder)
        // .publishOn() // OR instead of subscribeOn with Schedulers you can use publish
        .map(this.orderRepository::save) // store this object based in repository // BLOCKING
        .map(EntityDTOUtil::getPurchaseOrderResponseDTO)
        .subscribeOn(Schedulers.boundedElastic()); //because (save) JPA is blocking we have
    // to use subscribe with Schedulers and elastic to have a dedicated thread pool. so, blocking operation will not affect the pipeline
  }

  /**
   *
   * @param requestContext
   * @return
   *
   * build request context
   * made request to product service to get product information
   *
   * CREATED a context that, based on the product, GOT the product.
   * this context now contains purchase request and the product
   * KNOW user product and product price
   * POST the tx
   * SEND the tx request to user service to detect the amount to send request
   * BUILD the tx request DTO
   */
  private Mono<RequestContext> productRequestResponse(RequestContext requestContext) {
    return this.productCliente.getProductById(requestContext.getPurchaseOrderRequestDTO().getProductId())
        .doOnNext(requestContext::setProductDTO)
        .thenReturn(requestContext);
  }

  /**
   *
   * @param requestContext
   * @return
   *
   * SENd request to user service to detect AMOUNT
   *
   */
  private Mono<RequestContext> userRequestResponse(RequestContext requestContext) {
    return this.userClient.authorizeTransaction(requestContext.getTransactionRequestDTO())
        .doOnNext(requestContext::setTransactionResponseDTO)
        .thenReturn(requestContext);
  }

}
