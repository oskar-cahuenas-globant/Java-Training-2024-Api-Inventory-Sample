package com.globant.training.inventorysample.service;

import com.globant.training.inventorysample.domain.dto.CustomerDto;

import java.util.List;

/**
 * Contract for service of Customer entity
 */
public interface ICustomerService {
  /**
   * Finds all customers in DB
   * @return a List of all products in DB
   */
  List<CustomerDto> findAllCustomers();

  /**
   * Finds customer in DB for given SKU code.
   *
   * @param document documentNumber to be found
   * @return CustomerDto instance with customer data or exception it
   * customer not found.
   */
  CustomerDto findCustomerByDocument(String document);

  /**
   * Insert a new customer record in DB.
   * @param customerDto DTO with the customer data to be inserted
   * @return DTO with data or the customer inserted or throw  exception
   * if product Document already exists
   */
  CustomerDto createCustomer(CustomerDto customerDto);
}
