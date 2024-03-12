package com.globant.training.inventorysample.controller;

import com.globant.training.inventorysample.domain.dto.ProductDto;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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

  /**
   * Creates a new product in DB and return the DTO that represents that object
   *
   * @param product The product to be created
   * @return The product DTO with all fields saved in DB. If there are some validation
   * error, an error 400 will be thrown. If already exists a product with the SKU
   * an error 409 will be thrown
   */
  @PostMapping(value = "/products", produces = MediaType.APPLICATION_JSON_VALUE, consumes =
      MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto product);
}
