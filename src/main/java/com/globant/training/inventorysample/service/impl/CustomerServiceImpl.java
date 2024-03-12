package com.globant.training.inventorysample.service.impl;

import com.globant.training.inventorysample.domain.dto.CustomerDto;
import com.globant.training.inventorysample.domain.entity.Customer;
import com.globant.training.inventorysample.exceptions.withexceptionhandler.CustomerDocumentAlreadyExistException;
import com.globant.training.inventorysample.exceptions.withexceptionhandler.CustomerNotFoundException;
import com.globant.training.inventorysample.mapper.CustomerDtoToCustomerEntityConverter;
import com.globant.training.inventorysample.mapper.CustomerEntityToCustomerDtoConverter;
import com.globant.training.inventorysample.repository.CustomerRepository;
import com.globant.training.inventorysample.service.ICustomerService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
  private static final Logger LOGGER = LoggerFactory.getLogger(CustomerServiceImpl.class);

  private final CustomerRepository customerRepository;
  private final CustomerEntityToCustomerDtoConverter customerEntityToCustomerDtoConverter;
  private final CustomerDtoToCustomerEntityConverter customerDtoToCustomerEntityConverter;

  @Override
  public List<CustomerDto> findAllCustomers() {
    LOGGER.info("Begin method findAllCustomers");
    return customerRepository
        .findAll()
        .stream()
        .map(customerEntityToCustomerDtoConverter::convert)
        .toList();
  }

  @Override
  public CustomerDto findCustomerByDocument(String document) {
    LOGGER.info("Begin method findCustomerByDocument document={}", document);
    Optional<Customer> customer = customerRepository.findByDocument(document);

    if (customer.isEmpty()) {
      LOGGER.error(
          "method findCustomerByDocument customer with document={} throws CustomerNotFoundException", document);

      // use exception hierarchy for business rules validaiton in service
      throw new CustomerNotFoundException(
          String.format("Customer with document=%s not found", document));
    }
    return customerEntityToCustomerDtoConverter.convert(
        customer.get());
  }

  @Override
  public CustomerDto createCustomer(CustomerDto customerDto) {
    LOGGER.info("Begin method createCustomer dto={}", customerDto);
    final String document = customerDto.getDocumentType() + "-" + customerDto.getDocumentNumber();
    if (customerRepository.existsByDocument(document)) {
      // use exception hierarchy for business rules validaiton in service
      LOGGER.error(
          "method createCustomer with document={} throws CustomerDocumentAlreadyExistException", document);
      throw new CustomerDocumentAlreadyExistException(
          String.format("document=%s already exists in DB", document));
    }
    customerRepository.save(customerDtoToCustomerEntityConverter.convert(customerDto));
    return customerDto;
  }
}
