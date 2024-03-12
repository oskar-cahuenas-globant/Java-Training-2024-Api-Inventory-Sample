package com.globant.training.inventorysample.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customers")
public class Customer {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(nullable = false)
  private Long id;

  @Column(name = "customersDocument", nullable = false, unique = true)
  private String document;

  @Column(name = "customersName", nullable = false)
  private String name;

  @Column(name = "customersAddress", nullable = false)
  private String address;

  @Column(name = "customersPhone", nullable = false)
  private String phone;

  @Column(name = "customersBirthday", nullable = false)
  private LocalDate birthday;
}
