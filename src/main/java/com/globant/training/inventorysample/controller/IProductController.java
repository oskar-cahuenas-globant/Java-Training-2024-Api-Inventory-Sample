package com.globant.training.inventorysample.controller;

import com.globant.training.inventorysample.domain.dto.ProductDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * Product controller contract
 */
public interface IProductController {
  /**
   * Returns all products from DB with no specific order
   *
   * @return List of all products in DB
   */
  @GetMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<ProductDto>> findAllProducts();

  /**
   * Returns a product by sku code
   *
   * @param sku SKU to be searched
   * @return The product instance or error 404 if SKU is not found
   */
  @GetMapping(value = "/products/{sku}", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<ProductDto> findProductBySku(@PathVariable("sku") String sku);
}
