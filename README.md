# Java-Training-2024-Api-Inventory-Example

Example of basic Restful API with Springboot

# Configuration

Application is set to listen by default on port 9090. It could be changed by editing property `server.port`.

It works out of the box by creating an in-memory H2 database. Some seeding data is provided.

To run the project you should execute the following commands in your shell:

```
mvn clean package

mvn spring-boot:run
```

To stop the application, the following command should be issued in your shell:

```
mvn spring-boot:stop
```


File `insomnia_project/Insomnia_api_sample.json` contains an Insomnia project with exaple endpoints.

# API Contract

TODO: Generate Swagger documentation.

Idea of API: Inventory of product for a Clothes and apparel store.

## Product DTO

Attributes:

- `sku`: `String` with product inventory SKU Code. It must be unique.
- `name`: `String` with product name.
- `description`: `String` with longer product description.
- `category`: Enumeration with product category. Valid values from
  Java `enum` `com.globant.training.inventorysample.domain.enumerations.ProductCategoryEnum`.
- `color`: Color of the product. Valid values from
  Java `enum` `com.globant.training.inventorysample.domain.enumerations.ColorEnum`.
- `unitPrice`: `Double` value with price of product in USD.
- `availbale`: `Boolean` flag for availability of product to sale.

````
{
  "sku": "P-0009",
  "name": "White Unisex T-Shirt",
  "category": "SHIRTS_AND_T_SHIRTS",
  "description": "Unisex T-Shirt Color white short sleeve Size M,
  "unitPrice": 35.99,
  "color": "WHITE",
  "available": true
}
````

## GET /products

Return a list of all products.

## GET /products/{sku}

Return the product with SKU in parameters. Returns `404` if product does not exist in catalog.

# Example classes

## Package Scan configuration

Main application class `com.globant.training.inventorysample.Application`.

## Dto Mappers

In package `com.globant.training.inventorysample.mapper` there are a couple of examples of mappers using
Spring's `Converter<S, T>`interface.

## Controler Interface and definition

- Class `com.globant.training.inventorysample.controller.IProductController` defines controller contract.
- Class `com.globant.training.inventorysample.controller.impl.ProductControllerImpl` defines controller implementation.

## Service Interface and definition

- Class `com.globant.training.inventorysample.service.IProductService` defines service contract.
- Class `com.globant.training.inventorysample.service.impl.ProductServiceImpl` defines service implementation.

## JPA Entities

JPA entities are Java classes with aditional annotations to be mapped to a Relational SQL Database. JPA entities allows
to perform crud opertations against relational databas use JPA/Hibernate specification.

Classes in package `com.globant.training.inventorysample.domain.entities` are examples of DTOs

## DTOs Entities

DTOs are objects used to communicate date from and to HTTP Request/Response. DTO allows to transform data between
Controller and service layes as well.

Classes in package `com.globant.training.inventorysample.domain.dto` are examples of DTOs

## Product repository interface with two "magic" methods defined

- Interface `com.globant.training.inventorysample.repository.ProductRepository` Defines a JPA repository for Product
  Entity with query or "magic methods" for lookup by entity field name.
