package com.globant.training.inventorysample.exceptions.withexceptionhandler;

/**
 * Generic not found error when entity tried to be created an already exists
 *
 * You should inherit all NotFound exceptions for this class.
 */
public abstract class EntityNotFoundExceptionBase extends BaseInventoryApiException {
  public EntityNotFoundExceptionBase(String message) {
    super(message);
  }
}
