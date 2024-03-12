package com.globant.training.inventorysample.domain.enumerations;

/**
 * Enumeration for Products' category
 * with an aditional information property.
 */

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum ProductCategoryEnum {
  PANTS_AND_SHORTS("pants and showts"),
  SHIRTS_AND_T_SHIRTS("shirts, t-shirts"),
  DRESSES("women dresses"),
  UNDERWEAR("underwear"),
  FOOTWEAR("shoes, boots");

  private final String description;
}
