package com.globant.training.inventorysample.exceptions.withstatuscode;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception not found to show the standard spring boot exception handling. When this exception is thrown by
 * controller, then the standard error object of Springboot will be returned un HTTP response, and the response code
 * will be the one set in Annotation. In this case, with this exception controller will issue a 404 - NOT FOUND response
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ProductNotExistException extends RuntimeException {
  public ProductNotExistException(String message) {
    super(message);
  }
}
