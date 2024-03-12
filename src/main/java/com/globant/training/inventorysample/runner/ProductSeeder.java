package com.globant.training.inventorysample.runner;

import com.globant.training.inventorysample.domain.entity.Product;
import com.globant.training.inventorysample.domain.enumerations.ColorEnum;
import com.globant.training.inventorysample.domain.enumerations.ProductCategoryEnum;
import com.globant.training.inventorysample.repository.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Seeder class for filling the catalog with initial data.
 */
@AllArgsConstructor
@Service
@Order(1)  // order of execution of the beans by spring framework
// in order to avoid this runner be executed while testing
// add command line -Dspring.profiles.active=test
@Profile("!test")
public class ProductSeeder implements CommandLineRunner {

  ProductRepository productRepository;

  // annotate with transactional for the case
  // of insert with relations
  @Override
  @Transactional
  public void run(String... args) throws Exception {
    // generated data
    // we will insert some products
    productRepository.saveAll(
        List.of(
            Product.builder()
                .sku("P-0001")
                .name("Male Blue Jean Size M")
                .description("Male Blue Jean Classic Size M")
                .unitPrice(35.99)
                .available(true)
                .productCategory(ProductCategoryEnum.PANTS_AND_SHORTS)
                .color(ColorEnum.BLUE.toString())
                .build(),
            Product.builder()
                .sku("P-0002")
                .name("Male Blue Jean Size L")
                .description("Male Blue Jean Classic Size L")
                .unitPrice(35.99)
                .available(true)
                .productCategory(ProductCategoryEnum.PANTS_AND_SHORTS)
                .color(ColorEnum.BLUE.toString())
                .build(),
            Product.builder()
                .sku("P-0003")
                .name("Male Blue Jean Size XL")
                .description("Male Blue Jean Classic Size XL")
                .unitPrice(39.99)
                .available(true)
                .productCategory(ProductCategoryEnum.PANTS_AND_SHORTS)
                .color(ColorEnum.BLUE.toString())
                .build(),
            Product.builder()
                .sku("P-0004")
                .name("Female Blue Jean Size 10")
                .description("Female Blue Jean Classic Size 10")
                .unitPrice(33.99)
                .available(true)
                .productCategory(ProductCategoryEnum.PANTS_AND_SHORTS)
                .color(ColorEnum.BLUE.toString())
                .build(),
            Product.builder()
                .sku("P-0005")
                .name("Female Blue Jean Size 12")
                .description("female Blue Jean Classic Size M")
                .unitPrice(33.99)
                .available(true)
                .productCategory(ProductCategoryEnum.PANTS_AND_SHORTS)
                .color(ColorEnum.BLUE.toString())
                .build(),
            Product.builder()
                .sku("P-0006")
                .name("Black Polo T-shirt")
                .description("Black Polo Tshirt short sleeve size S")
                .unitPrice(20.99)
                .available(true)
                .productCategory(ProductCategoryEnum.SHIRTS_AND_T_SHIRTS)
                .color(ColorEnum.BLACK.toString())
                .build(),
            Product.builder()
                .sku("P-0007")
                .name("Red Polo T-shirt")
                .description("Red Polo Tshirt short sleeve size S")
                .unitPrice(20.99)
                .available(true)
                .productCategory(ProductCategoryEnum.SHIRTS_AND_T_SHIRTS)
                .color(ColorEnum.RED.toString())
                .build(),
            Product.builder()
                .sku("P-008")
                .name("Red Polo T-shirt")
                .description("Orange Polo Tshirt short sleeve size S")
                .unitPrice(20.99)
                .available(true)
                .productCategory(ProductCategoryEnum.PANTS_AND_SHORTS)
                .color(ColorEnum.ORANGE.toString())
                .build()));
  }
}
