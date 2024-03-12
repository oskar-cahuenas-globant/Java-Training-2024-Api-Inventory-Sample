package com.globant.training.inventorysample.service.impl;

import com.globant.training.inventorysample.domain.dto.ProductDto;
import com.globant.training.inventorysample.domain.entity.Product;
import com.globant.training.inventorysample.exceptions.withstatuscode.ProductNotExistException;
import com.globant.training.inventorysample.mapper.ProductDtoToProductEntityConverter;
import com.globant.training.inventorysample.mapper.ProductEntityToProductDtoConverter;
import com.globant.training.inventorysample.repository.ProductRepository;
import com.globant.training.inventorysample.service.IProductService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
  private final ProductDtoToProductEntityConverter productDtoToProductEntityConverter;

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
    Optional<Product> product = productRepository.findBySku(sku);

    // In this example if product not found
    // an exception with @ResponseStatus
    // and then an error with standard Spring boot error object will be thrown
    if (product.isEmpty()) {
      // exception anotated with NOT_FOUND response code
      LOGGER.error(
          "method findProductBySku product with sku={} throws ProductNotExistException", sku);
      throw new ProductNotExistException(String.format("Product with SKU=%s not found", sku));
    }
    return productEntityToProductDtoConverter.convert(product.get());
  }

  @Override
  public ProductDto createProduct(ProductDto product) {
    LOGGER.info("Begin method createProduct dto={}", product);
    final String sku = product.getSku().trim();
    // verify if SKU is already registered in Database
    // then throws exception
    // in this iteration a RuntimeException is thrown
    // in a next iteration a proper exception handler will be implemented
    if (productRepository.existsBySku(sku)) {
      LOGGER.error(
          "method findProductBySku with SKU={} throws ProductSkuAlreadyExistException", sku);
      throw new RuntimeException(
          String.format("SKU=%s already exists in DB", sku));
    }
    productRepository.save(productDtoToProductEntityConverter.convert(product));
    return product;
  }
}
