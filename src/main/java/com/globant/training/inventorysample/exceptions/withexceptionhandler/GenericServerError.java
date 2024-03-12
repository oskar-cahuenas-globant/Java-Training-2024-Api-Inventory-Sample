package com.globant.training.inventorysample.exceptions.withexceptionhandler;

/**
 * Generic not managed error exception
 *
 * For all not managed errors.
 */
public class GenericServerError extends BaseInventoryApiException {
  public GenericServerError(String message, Throwable cause) {
    super(message, cause);
  }

}
