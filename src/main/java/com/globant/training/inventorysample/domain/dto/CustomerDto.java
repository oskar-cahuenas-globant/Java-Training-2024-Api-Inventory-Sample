package com.globant.training.inventorysample.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
  private String documentType;
  private String documentNumber;
  private String name;
  private String address;
  private String phone;
  private LocalDate birthday;
}
