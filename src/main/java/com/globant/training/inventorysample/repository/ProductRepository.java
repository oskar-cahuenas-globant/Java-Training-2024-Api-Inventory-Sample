package com.globant.training.inventorysample.repository;

import com.globant.training.inventorysample.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Spring JPA repository for Product witn primary key long
 * <p>
 * The idea is to not implement methods. Frameworks implements automatically all the logic
 * for generating SQL queries and execute them at server.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
  // automagic method to find first by SKU
  Optional<Product> findBySku(String sku);

  // Automagic method to test if exist by SKU
  boolean existsBySku(String sku);
}
