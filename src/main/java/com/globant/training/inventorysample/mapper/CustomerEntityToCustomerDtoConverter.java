package com.globant.training.inventorysample.mapper;
import com.globant.training.inventorysample.domain.dto.CustomerDto;
import com.globant.training.inventorysample.domain.entity.Customer;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CustomerEntityToCustomerDtoConverter implements Converter<Customer, CustomerDto> {
  @Override
  public CustomerDto convert(Customer source) {
    String[] documentFields = source == null
        ? null
        : source.getDocument().split("\\-");

    return source == null
        ? null
        : CustomerDto
        .builder()
        .documentType(documentFields[0])
        .documentNumber(documentFields[1])
        .name(source.getName())
        .address(source.getAddress())
        .phone(source.getPhone())
        .birthday(source.getBirthday())
        .build();
  }
}
