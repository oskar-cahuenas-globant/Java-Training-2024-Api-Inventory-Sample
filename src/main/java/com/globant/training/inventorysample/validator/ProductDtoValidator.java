package com.globant.training.inventorysample.validator;

import com.globant.training.inventorysample.domain.dto.ProductDto;
import com.globant.training.inventorysample.domain.enumerations.ColorEnum;
import com.globant.training.inventorysample.domain.enumerations.ProductCategoryEnum;
import com.globant.training.inventorysample.exceptions.withexceptionhandler.ValidationExceptionBase;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Component with validators for product Dto.
 */
@Component
public class ProductDtoValidator {
  public void validateProductCreateDto(ProductDto product) {
    // list with error messages
    final List<String> errorList = new ArrayList<>();

    // verify if product is null
    if (product == null) {
      throw new ValidationExceptionBase("Product instance mustn't be null");
    }

    // TODO: Add more validation rules
    // validate only that are not empty
    // price must be > 0
    // validate sku
    if (!StringUtils.hasLength(StringUtils.trimAllWhitespace(product.getSku()))) {
      errorList.add("Sku code can't be empty");
    }
    // validate name
    if (!StringUtils.hasLength(StringUtils.trimAllWhitespace(product.getName()))) {
      errorList.add("Name code can't be empty");
    }
    // validate description
    if (!StringUtils.hasLength(StringUtils.trimAllWhitespace(product.getDescription()))) {
      errorList.add("Description code can't be empty");
    }
    // validate category must be a valid enumeration
    if (!ProductCategoryEnum.existsCategory(product.getCategory())) {
      errorList.add("Category must be a valid value of: " + ProductCategoryEnum.valuesList());
    }
    // validate color  must be a valid enumeration
    if (!ColorEnum.existColor(product.getColor())) {
      errorList.add("Color must be a valid value of: " + ColorEnum.valuesList());
    }
    // validate unit price
    if (product.getUnitPrice() == null || product.getUnitPrice() <= 0D) {
      errorList.add("Unit price must be > 0");
    }

    if (product.getAvailable() == null) {
      errorList.add("available must be true or false");
    }

    // it there is some error, a validation exception is throws
    if (!errorList.isEmpty()) {
      throw new ValidationExceptionBase(
          String.format("Validation exception: %s", String.join(", ", errorList)));
    }
  }
}
