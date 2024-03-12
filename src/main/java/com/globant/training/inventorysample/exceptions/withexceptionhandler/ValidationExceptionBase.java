package com.globant.training.inventorysample.exceptions.withexceptionhandler;
/**
 * Generic validation error in Dto.
 */
public class ValidationExceptionBase extends BaseInventoryApiException {
  public ValidationExceptionBase(String message) {
    super(message);
  }
}
