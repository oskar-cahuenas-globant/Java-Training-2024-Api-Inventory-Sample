package com.globant.training.inventorysample.mapper;
import com.globant.training.inventorysample.domain.dto.CustomerDto;
import com.globant.training.inventorysample.domain.entity.Customer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerDtoToCustomerEntityConverter implements Converter<CustomerDto, Customer> {
  @Override
  public Customer convert(CustomerDto source) {
    return source == null
        ? null
        : Customer
        .builder()
        .document(source.getDocumentType() + "-" + source.getDocumentNumber())
        .name(source.getName())
        .address(source.getAddress())
        .phone(source.getPhone())
        .birthday(source.getBirthday())
        .build();
  }
}
