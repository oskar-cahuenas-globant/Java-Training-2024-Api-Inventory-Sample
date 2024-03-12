package com.globant.training.inventorysample.mapper;
import com.globant.training.inventorysample.domain.dto.ProductDto;
import com.globant.training.inventorysample.domain.entity.Product;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter from Product to Product Dto
 */
@Component
public class ProductEntityToProductDtoConverter implements Converter<Product, ProductDto> {
  @Override
  public ProductDto convert(Product source) {
    return source == null
        ? null
        : ProductDto
        .builder()
        .sku(source.getSku().trim())
        .name(source.getName())
        .description(source.getDescription())
        .category(source.getProductCategory().toString())
        .unitPrice(source.getUnitPrice())
        .color(source.getColor())
        .available(source.getAvailable())
        .build();
  }
}
