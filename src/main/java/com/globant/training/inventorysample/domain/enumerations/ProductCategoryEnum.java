package com.globant.training.inventorysample.domain.enumerations;

/**
 * Enumeration for Products' category
 * with an aditional information property.
 */

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@AllArgsConstructor
public enum ProductCategoryEnum {
  PANTS_AND_SHORTS("pants and showts"),
  SHIRTS_AND_T_SHIRTS("shirts, t-shirts"),
  DRESSES("women dresses"),
  UNDERWEAR("underwear"),
  FOOTWEAR("shoes, boots");

  private final String description;

  private static final Map<String, ProductCategoryEnum> CATEGORY_BY_NAME;

  static {
    CATEGORY_BY_NAME = new HashMap<>();
    for (ProductCategoryEnum v: values()) {
      CATEGORY_BY_NAME.put(v.toString().toUpperCase(), v);
    }
  }

  /**
   * Test if a string with is a valid enumeration value.
   * @param category String with enumerated value as string.
   * @return boolean if category with that name exists in enum.
   */
  public static boolean existsCategory(String category) {
    return category != null && CATEGORY_BY_NAME.containsKey(category.toUpperCase());
  }

  /**
   * Return string with list of values of this enum.
   * @return String representation of list of comma-separated values.
   */
  public static String valuesList() {
    return Arrays.stream(values())
        .sequential()
        .map(ProductCategoryEnum::toString)
        .collect(Collectors.joining(", "));
  }
}
