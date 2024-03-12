package com.globant.training.inventorysample.exceptions.withexceptionhandler;
public class CustomerDocumentAlreadyExistException extends EntityAlreadyExistExceptionBase {
  public CustomerDocumentAlreadyExistException(String message) {
    super(message);
  }
}
