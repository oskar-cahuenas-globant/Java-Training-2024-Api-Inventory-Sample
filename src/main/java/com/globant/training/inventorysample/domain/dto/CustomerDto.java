package com.globant.training.inventorysample.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * Example of Dto with validation annotation
 * <p>
 * - Each property could be annotated with one or more validations
 * - Validations could have attributes and a message
 * - Validation are applied in textual order
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDto {
  // document type not null and only valid CC or CE
  @NotNull(message = "documentType is missing")
  @Pattern(regexp = "CC|CE", message = "Document type must be CC or CE")
  private String documentType;

  // document type not null and only digits 4 to 12 of length
  @NotNull(message = "documentType is missing")
  @Size(min = 4, max = 12, message = "Document number must be between 4 and 12 chars long")
  @Pattern(regexp = "\\d+", message = "Document number must have only digits")
  private String documentNumber;

  // Name is no blank max 255 chars
  @NotBlank(message = "Name is mandatory and can't be empty")
  @Size(max = 255, message = "Name could be at most 255 characters long")
  private String name;

  // address min 10 chars and not empty
  @NotNull
  @Size(min = 10, message = "Address must be at least 10 chars long")
  private String address;

  // phone must be 10 digits
  @Pattern(regexp = "\\d{10}", message = "phone must be 10 digits long")
  private String phone;

  // birthday must be a past date
  @NotNull(message = "birthday is missing")
  @Past(message = "Birthday must be a past date")
  private LocalDate birthday;
}
