package com.globant.training.inventorysample.mapper;
import com.globant.training.inventorysample.domain.dto.ProductDto;
import com.globant.training.inventorysample.domain.entity.Product;
import com.globant.training.inventorysample.domain.enumerations.ProductCategoryEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter  from ProductDto to Product.
 */
@Component
public class ProductDtoToProductEntityConverter implements Converter<ProductDto, Product> {
  @Override
  public Product convert(ProductDto source) {
    return source == null
        ? null
        : Product
        .builder()
        .sku(source.getSku().trim())
        .name(source.getName())
        .description(source.getDescription())
        .productCategory(
            ProductCategoryEnum.valueOf(source.getCategory().toUpperCase()))
        .unitPrice(source.getUnitPrice())
        .color(source.getColor().toUpperCase())
        .available(source.getAvailable())
        .build();
  }
}
