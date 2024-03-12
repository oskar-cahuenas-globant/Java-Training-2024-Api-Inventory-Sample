package com.globant.training.inventorysample.exceptions.withexceptionhandler;
public class ProductSkuAlreadyExistException extends EntityAlreadyExistExceptionBase {
  public ProductSkuAlreadyExistException(String message) {
    super(message);
  }
}
