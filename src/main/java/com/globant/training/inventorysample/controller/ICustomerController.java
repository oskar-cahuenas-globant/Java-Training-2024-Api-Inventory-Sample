package com.globant.training.inventorysample.controller;

import com.globant.training.inventorysample.domain.dto.CustomerDto;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * Customer controller contract
 */
public interface ICustomerController {
  /**
   * Returns all customers from DB with no specific order.
   *
   * @return List of all customers in DB.
   */
  @GetMapping(value = "/customers", produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<List<CustomerDto>> findAllCustomers();

  /**
   * Returns a customer by document.
   *
   * @param documentType   The document Type.
   * @param documentNumber The document Number.
   * @return The product instance or error 404 if customer by document is not found.
   */

  // validation annotations in url parameters
  @GetMapping(value = "/customers/{documentType}/{documentNumber}",
      produces = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<CustomerDto> findCustomerByDocument(
      @PathVariable("documentType")
      @Valid
      @NotNull(message = "document Type is missing")
      @Pattern(regexp = "(CC)|(CE)", message = "Document type must be CC or CE")
      String documentType,
      @PathVariable("documentNumber")
      @Valid
      @Pattern(regexp = "\\d{4,12}", message = "Document number must have only digits " +
          "and between 4 and 12 characters long")
      String documentNumber);

  /**
   * Creates a new customer in DB and return the DTO that represents that object.
   *
   * @param customer The customer data to be created
   * @return The customerDto with all fields saved in DB. If there are some validation
   * error, an error 400 will be thrown. If already exists a customer with the document
   * type and number an error 409 will be thrown
   */
  // @Valid for activating validators in method parameter
  @PostMapping(
      value = "/customers",
      produces = MediaType.APPLICATION_JSON_VALUE,
      consumes = MediaType.APPLICATION_JSON_VALUE)
  ResponseEntity<CustomerDto> createCustomer(
      @Valid @RequestBody CustomerDto customer);
}
