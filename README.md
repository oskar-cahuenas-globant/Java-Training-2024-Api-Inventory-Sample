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

## POST /products

Creates product with information in request body. Example of body:

````
{
  "sku": "P-18888",
  "name": "Pink short sleeve T-Shirt",
  "category": "SHIRTS_AND_T_SHIRTS",
  "description": "Female T-Shirt Pink short sleeve Size M,
  "unitPrice": 28.99,
  "color": "PINK",
  "available": true
}
````

## Customer  DTO

Attributes:

- `documentType`: `String` CC or CE.
- `documentType`: `String` with digits. Combination of type and number must be unique
- `name`: `String` Customer name and surname
- `address`: `String` Customer delivery address.
- `phone`: Phone number 10 digits.
- `birthday`: Birthday of Customer with format 'YYYY-MM-DD'

````
{
	"documentType": "CC",
	"documentNumber":"123456",
	"name": "pedro perez",
	"address": "Cra. 99 #20-33",
	"phone": "3211234567",
	"email": "pedro.perez@gmail.com",
	"birthday": "1980-02-20"
}	
````

## GET /customers

Return a list of all customer.

## GET /customers/{documentType}/{documentNumber}

Return the user with Document in parameters in the form {documentType}-{DocumentNumber}. Returns `404` if product does
not exist in catalog.

## POST /customers

Creates customer with information in request body. Example of body:

````
{
	"documentType": "CC",
	"documentNumber":"123456",
	"name": "pedro perez",
	"address": "Cra. 99 #20-33",
	"phone": "3211234567",
	"email": "pedro.perez@gmail.com",
	"birthday": "1980-02-20"
}	
````

# Example classes

## Package Scan configuration

Main application class `com.globant.training.inventorysample.Application`.

## Dto Mappers

In package `com.globant.training.inventorysample.mapper` there are a couple of examples of mappers using
Spring's `Converter<S, T>`interface.

## Controler Interface and definition

- Class `com.globant.training.inventorysample.controller.IProductController` defines controller contract.
- Class `com.globant.training.inventorysample.controller.impl.ProductControllerImpl` defines controller implementation.
- Class `com.globant.training.inventorysample.controller.ICustomerController` defines controller contract.
- Class `com.globant.training.inventorysample.controller.impl.CustomerControllerImpl` defines controller implementation.

## Service Interface and definition

- Class `com.globant.training.inventorysample.service.IProductService` defines service contract.
- Class `com.globant.training.inventorysample.service.impl.ProductServiceImpl` defines service implementation.
- Class `com.globant.training.inventorysample.service.ICustomerService` defines service contract.
- Class `com.globant.training.inventorysample.service.impl.CustomerServiceImpl` defines service implementation.

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

# Database initial Seeding

Example of initial seeding with a CommandLineRunner for populating tables

- Class `com.globant.training.inventorysample.runner.ProductSeeder` for populating `Products` table.
- Class `com.globant.training.inventorysample.runner.CustomerSeeder` for populating `Customer` table.

# Exception handling

## Exception with `@ResponseStatus` and standard response object

For the case of method `findProductBySku`  in class `com.globant.training.inventorysample.service.ProductServiceImpl`
not found error is handled with an exception annotated with @NOT_FOUND that causes controller respond with standard
error object and code 404.

## Error hierarchy

For implement ExceptionHandler is recommended to define an exception hierarchy for all posible exceptional conditions in
API Code. In example the package `com.globant.training.inventorysample.exceptions.withexceptionhandler` will define a
hierarchy of exceptions for the rest of the validation exercises.

The hierarchy is as follows

```
 + BaseInventoryApiException (Base exception of herarchy)
 |
 |
 +--- GenericServerError (Exception for a generic or unclasified error)
 |
 |
 +--- ValidationExceptionBase (Base of all input validation errors)
 |
 |
 +--- EntityNotFoundException (Base of all Not found exception for each entity)
 |    |
 |    |
 |    + -- ProductNotFoundException (Exception not found for Product entity)
 |    |
 |    |
 |    + -- CustomerNotFoundException (Exception not found for Customer entity)
 |
 +--- EntityAlreadyExistException (Base of all exception of existing entity while trying to insert)          
 |    |
 |    |
 |    + -- ProductSkuAlreadyExistException (Exception existing for Product entity by SKU)
 |    |
 |    |
 |    + -- CustomerDocumentAlreadyExistException (Exception existing for Customer entity by Document)
 
```

# Manual validator

In package `com.globant.training.inventorysample.validator` you will find an example of an "ad-hoc" validator
for `ProductDto` class with a single validation for testing that all attributes are not-empty.
There are a validation for SKU parameter of endpoint `/products/sku` as well.

In ProductController there are examples of Manual Validator use.

# Exception handler

Examples of exception handlers for manual validator using `@ControllerAdvice` and `@ExceptionHandler` annotations are in
class `com.globant.training.inventorysample.controller.ExceptionHandlerAdvicer` 


