package com.udemy.orderservice.client;

import com.udemy.orderservice.dto.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.*;
import org.springframework.web.reactive.function.client.*;
import reactor.core.publisher.*;

// send request to product service and get reponse
@Service
public class ProductCliente {

  private final WebClient webClient;

  // as part of the constructor we are building the webclient
  // it sends the request to product service and get the response
  public ProductCliente(@Value("${product.service.url}") String url) {
    this.webClient = WebClient.builder()
        .baseUrl(url)
        .build();
  }

  // based on Id we send the request to product service to retrieve the product information
  public Mono<ProductDTO> getProductById(final String productId) {
    return this.webClient
        .get()
        .uri("{id}", productId)
        .retrieve()
        .bodyToMono(ProductDTO.class);
  }

}
