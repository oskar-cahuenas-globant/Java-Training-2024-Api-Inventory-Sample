package com.globant.training.inventorysample.domain.enumerations;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Enumeration for products' colors/
 */
public enum ColorEnum {
  BLACK,
  WHITE,
  GRAY,
  RED,
  BLUE,
  GREEN,
  MAGENTA,
  MAROON,
  PINK,
  VIOLET,
  AQUAMARINE,
  NAVY,
  LILAC,
  BEIGE,
  ORANGE,
  SALMON,
  YELLOW,
  LAVENDER;

  private static final Map<String, ColorEnum> COLOR_BY_NAME;

  static {
    COLOR_BY_NAME = new HashMap<>();
    for (ColorEnum v: values()) {
      COLOR_BY_NAME.put(v.toString().toUpperCase(), v);
    }
  }

  /**
   * Test if a string with is a valid enumeration value.
   * @param color String with enumerated number.
   * @return boolean if color with that name exists in enum.
   */
  public static boolean existColor(String color) {
    return color != null && COLOR_BY_NAME.containsKey(color.toUpperCase());
  }

  /**
   * Return string with list of values of this enum.
   * @return String representation of list of comma-separated values.
   */
  public static String valuesList() {
    return Arrays.stream(values())
        .sequential()
        .map(ColorEnum::toString)
        .collect(Collectors.joining(", "));
  }
}
