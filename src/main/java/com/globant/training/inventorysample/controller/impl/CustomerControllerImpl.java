package com.globant.training.inventorysample.controller.impl;

import com.globant.training.inventorysample.controller.ICustomerController;
import com.globant.training.inventorysample.domain.dto.CustomerDto;
import com.globant.training.inventorysample.service.ICustomerService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
// annotation for activating JSR annotation validators
// must be set in implementation class
@Validated
public class CustomerControllerImpl implements ICustomerController {
  private final ICustomerService customerService;

  @Override
  public ResponseEntity<List<CustomerDto>> findAllCustomers() {
    return ResponseEntity.ok(customerService.findAllCustomers());
  }

  @Override
  public ResponseEntity<CustomerDto> findCustomerByDocument(
      String documentType,
      String documentNumber) {
    final String document = documentType + "-" + documentNumber;
    return ResponseEntity.ok(customerService.findCustomerByDocument(document));
  }

  @Override
  public ResponseEntity<CustomerDto> createCustomer(CustomerDto customer) {
    return ResponseEntity
        .status(HttpStatus.CREATED)
        .body(customerService.createCustomer(customer));
  }
}
