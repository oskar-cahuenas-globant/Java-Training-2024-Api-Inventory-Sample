package com.globant.training.inventorysample.domain.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
  private String sku;
  private String name;
  private String category;
  private String description;
  private Double unitPrice;
  private String color;
  private Boolean available;
}
