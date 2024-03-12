package com.globant.training.inventorysample.runner;

import com.globant.training.inventorysample.domain.entity.Customer;
import com.globant.training.inventorysample.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

/**
 * Seeder class for filling the catalog with initial data.
 */
@AllArgsConstructor
@Component
@Order(2)
// Seeder dont work for unit test
@Profile("!test")
public class CustomerSeeder implements CommandLineRunner {

  CustomerRepository customerRepository;

  // annotate with transactional for the case
  // of insert with relations
  @Override
  @Transactional
  public void run(String... args) throws Exception {
    // generated data
    // we will insert some customers
    customerRepository.saveAll(
        List.of(
            Customer.builder()
                .document("CC-100999888")
                .name("Javier Perez")
                .address("Calle 12 #86-21")
                .phone("3198887776")
                .birthday(LocalDate.of(1990, Month.FEBRUARY,15))
                .build(),
            Customer.builder()
                .document("CC-100777444")
                .name("Camila Fernandez")
                .address("Kr 12 #145-13")
                .phone("3194328899")
                .birthday(LocalDate.of(2000, Month.SEPTEMBER,3))
                .build()));
  }
}
