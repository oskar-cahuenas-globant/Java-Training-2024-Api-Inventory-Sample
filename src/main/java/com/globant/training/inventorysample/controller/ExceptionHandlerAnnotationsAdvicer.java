package com.globant.training.inventorysample.controller;

import com.globant.training.inventorysample.domain.dto.ErrorDto;
import com.globant.training.inventorysample.exceptions.withexceptionhandler.EntityAlreadyExistExceptionBase;
import com.globant.training.inventorysample.exceptions.withexceptionhandler.ValidationExceptionBase;
import jakarta.validation.ConstraintViolationException;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Exception advice use to handle exceptions from validations with JSR annotations
 */
@Component
@RestControllerAdvice
@Order(1) // this has more priority than other controller advicers
public class ExceptionHandlerAnnotationsAdvicer {
  /**
   * Handlers for JSR Validators
   * JSR Validations could fail with two exceptions
   *
   * - MethodArgumentNotValidException
   * - ConstraintViolationException
   */

  // MethodArgumentNotValidException fails valiadting RequestBody variables
  @ExceptionHandler({MethodArgumentNotValidException.class})
  public ResponseEntity<ErrorDto> handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(createErrorDto("JSR validation error", e));
  }


  // ConstraintViolationException fails valiadting PathVariable or RequestParam variables
  @ExceptionHandler({ConstraintViolationException.class})
  public ResponseEntity<ErrorDto> handlerMethodConstraintViolationException(ConstraintViolationException e) {

    return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(createErrorDto("JSR validation error", e));
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
