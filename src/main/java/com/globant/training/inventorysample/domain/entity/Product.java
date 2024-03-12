package com.globant.training.inventorysample.domain.entity;

import com.globant.training.inventorysample.domain.enumerations.ProductCategoryEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity  // I am a DB entity
@Table(name = "products") // Configs of the table
public class Product {
  // Mandatory for defining primary key
  // Strategy for generation in the case of H2 and mySQL is
  // an identity or auto_increment Long integer
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  // Column annotation for optional column metadata
  // if the name of the column is different in DB you could set the value
  // here
  @Column(name = "productsSku", nullable = false, length = 100)
  private String sku;

  @Column(name = "productsName", nullable = false, length = 255)
  private String name;

  // Enumerated annotation to inform that type is an enumerated
  // in DB there are two ways to store enumerations
  // ORDINAL = Store the column as an integer. More efficient but harder to read
  // STRING = Store the column as a VARCHAR o specific ENUM type. Less efficient (DB Dependent), easier to read
  @Enumerated(EnumType.ORDINAL)
  @Column(name = "productsCategory", nullable = false)
  private ProductCategoryEnum productCategory;

  @Column(name = "productsDescription", nullable = false, length = 512)
  private String description;

  @Column(name = "productsUnitPrice", nullable = false)
  private Double unitPrice;

  @Column(name = "productsColor", nullable = false, length = 100)
  private String color;

  @Column(name = "productsAvailable", nullable = false)
  private Boolean available;
}
