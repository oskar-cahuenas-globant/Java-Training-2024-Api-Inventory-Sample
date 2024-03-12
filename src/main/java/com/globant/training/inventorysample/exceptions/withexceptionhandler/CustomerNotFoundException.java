package com.globant.training.inventorysample.exceptions.withexceptionhandler;
public class CustomerNotFoundException extends EntityNotFoundExceptionBase {
  public CustomerNotFoundException(String message) {
    super(message);
  }
}
