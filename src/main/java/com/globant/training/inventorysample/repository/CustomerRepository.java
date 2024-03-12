package com.globant.training.inventorysample.repository;

import com.globant.training.inventorysample.domain.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
  Optional<Customer> findByDocument(String document);
  boolean existsByDocument(String document);
}
