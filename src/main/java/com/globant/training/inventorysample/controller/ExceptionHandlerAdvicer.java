package com.globant.training.inventorysample.controller;


import com.globant.training.inventorysample.domain.dto.ErrorDto;
import com.globant.training.inventorysample.exceptions.withexceptionhandler.EntityAlreadyExistExceptionBase;
import com.globant.training.inventorysample.exceptions.withexceptionhandler.ValidationExceptionBase;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Exception advice use to handle all controllers' exceptions.
 */
@Component
@RestControllerAdvice
@Order(2) // this has less priority respect handler of validation with JSR annotations
public class ExceptionHandlerAdvicer {
  /**
   * Exception handler for ValidaitonException errors
   * In this case response to be returned will be 400
   *
   * @param e Exception thrown
   * @return Response Entity with proper error object
   */
  @ExceptionHandler({ValidationExceptionBase.class})
  public ResponseEntity<ErrorDto> handlerValidationException(ValidationExceptionBase e) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST.value())
        .body(createErrorDto("Dto validation error", e));
  }

  /**
   * Exception handler for EntityAlreadyExistException errors
   * You must create an EntityAlreadyExistException child exception
   * for specific not found entity.
   * <p>
   * In this case response to be returned will be 409
   *
   * @param e Exception thrown
   * @return Response Entity with proper error object
   */
  @ExceptionHandler({EntityAlreadyExistExceptionBase.class})
  public ResponseEntity<ErrorDto> handlerEntityAlreadyExistException(EntityAlreadyExistExceptionBase e) {
    return ResponseEntity
        .status(HttpStatus.CONFLICT)
        .body(createErrorDto("entity already exists: ", e));
  }

  /**
   * Exception handler for generic error, and response will be 500
   * <p>
   * This method ALWAYS must be THE LAST ExceptionHandler in the class.
   *
   * @param e Exception thrown
   * @return Response Entity with proper error object
   */
  @ExceptionHandler({Exception.class})
  public ResponseEntity<ErrorDto> handlerGeneric(Exception e) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(createErrorDto("generic error", e));
  }

  /**
   * Creates error with timestamp and generic uuid.
   * @param title title for message.
   * @param e exception thrown.
   * @return ErrorDto instance.
   */
  private ErrorDto createErrorDto(String title, Exception e) {
    return ErrorDto
        .builder()
        .uuid(UUID.randomUUID().toString())
        .timeStamp(LocalDateTime.now().toString())
        .message(String.format("%s: %s", title, e.getMessage()))
        .build();
  }
}
