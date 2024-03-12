package com.globant.training.inventorysample.service.impl;

import com.globant.training.inventorysample.domain.dto.ProductDto;
import com.globant.training.inventorysample.mapper.ProductEntityToProductDtoConverter;
import com.globant.training.inventorysample.repository.ProductRepository;
import com.globant.training.inventorysample.service.IProductService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Product service implementation.
 */
@Service
@AllArgsConstructor
public class ProductServiceImpl implements IProductService {
  // logger instance
  private static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

  private final ProductRepository productRepository;
  private final ProductEntityToProductDtoConverter productEntityToProductDtoConverter;

  @Override
  public List<ProductDto> findAllProducts() {
    LOGGER.info("Begin method findAllProducts");
    return productRepository
        .findAll()
        .stream()
        .map(productEntityToProductDtoConverter::convert)
        .toList();
  }

  @Override
  public ProductDto findProductBySku(String sku) {
    LOGGER.info("Begin method findProductBySku sku={}", sku);
    return productRepository
        .findBySku(sku)
        .stream()
        .map(productEntityToProductDtoConverter::convert)
        //  by new we will throw an exception with a generic message
        // it will throw a 500 error
        // in a future iteration we will handle exceptions properly
        .findFirst()
        .orElseThrow(() -> new RuntimeException(String.format("Product with SKU=%s not found", sku)));
  }
}
