package com.globant.training.inventorysample.exceptions.withexceptionhandler;

/**
 * Generic conflict error when entity tried to be created an already exists
 *
 * You should inherit all AlreadyExists exceptions for this class.
 */
public abstract class EntityAlreadyExistExceptionBase extends BaseInventoryApiException {
  public EntityAlreadyExistExceptionBase(String message) {
    super(message);
  }
}
