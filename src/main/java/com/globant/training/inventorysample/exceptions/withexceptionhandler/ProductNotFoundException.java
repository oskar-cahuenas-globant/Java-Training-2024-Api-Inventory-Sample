package com.globant.training.inventorysample.exceptions.withexceptionhandler;

public class ProductNotFoundException extends EntityNotFoundExceptionBase {
  public ProductNotFoundException(String message) {
    super(message);
  }
}
