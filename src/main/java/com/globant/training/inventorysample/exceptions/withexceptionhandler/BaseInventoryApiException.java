package com.globant.training.inventorysample.exceptions.withexceptionhandler;
/**
 * Base exception for applications.
 *
 * All exception should inherit from this class.
 */
public abstract class BaseInventoryApiException extends RuntimeException {
  public BaseInventoryApiException(String message) {
    super(message);
  }

  public BaseInventoryApiException(String message, Throwable cause) {
    super(message, cause);
  }
}
