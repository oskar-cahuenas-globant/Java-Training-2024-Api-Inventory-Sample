package com.globant.training.inventorysample.controller.impl;

import com.globant.training.inventorysample.controller.IProductController;
import com.globant.training.inventorysample.domain.dto.ProductDto;
import com.globant.training.inventorysample.service.IProductService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Implmentation of  product controller.
 */
@RestController()
@AllArgsConstructor
public class ProductControllerImpl implements IProductController {
  // logger instance
  private static final Logger LOGGER = LoggerFactory.getLogger(ProductControllerImpl.class);

  IProductService productService;

  @Override
  public ResponseEntity<List<ProductDto>> findAllProducts() {
    LOGGER.info("Begin method findAllProducts");
      return ResponseEntity.ok(productService.findAllProducts());
  }

  @Override
  public ResponseEntity<ProductDto> findProductBySku(String sku) {
    LOGGER.info("Begin method findProductBySku");
    return ResponseEntity.ok(productService.findProductBySku(sku));
  }
}
