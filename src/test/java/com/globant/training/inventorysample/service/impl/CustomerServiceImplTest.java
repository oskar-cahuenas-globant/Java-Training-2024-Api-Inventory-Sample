package com.globant.training.inventorysample.service.impl;

import com.globant.training.inventorysample.domain.dto.CustomerDto;
import com.globant.training.inventorysample.domain.entity.Customer;
import com.globant.training.inventorysample.exceptions.withexceptionhandler.BaseInventoryApiException;
import com.globant.training.inventorysample.exceptions.withexceptionhandler.CustomerNotFoundException;
import com.globant.training.inventorysample.mapper.CustomerDtoToCustomerEntityConverter;
import com.globant.training.inventorysample.mapper.CustomerEntityToCustomerDtoConverter;
import com.globant.training.inventorysample.repository.CustomerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

  //  definir particiones de equivalencia
  // 1) documento existe en BD --> convierte a Dto y devuelve Dto
  // 2) documento no existe en BD --> Lanza excepción
  // 3) falla el repositorio por fallo de mySQL --> lanzar una RuntimeException

  // declarar las dependencias de la clase

  // service donde voy a hacer las pruebas
  // inyecta en el bean todas las dependencias que vengan en el constructors
  private CustomerServiceImpl service;
  // si desean pueden usar
  // @InjectMocks
  // private CustomerServiceImpl service;
  // y se hace solo el setUp, podrían omitir el otro método

  // dependencias del service como mocks
  @Mock
  private CustomerRepository customerRepositoryMock;
  @Mock
  private CustomerEntityToCustomerDtoConverter customerEntityToDtoConverterMock;
  @Mock
  private CustomerDtoToCustomerEntityConverter customerDtoToEntityConverterMock;

  // Los @ArgumentCaptor sirven para capturar los valores de argumentos
  // que reciben los mocks y así validar que "a lo interno" del me'todo
  // las operaciones intermedias reciben valores correctos.
  @Captor
  ArgumentCaptor<String> documentNumberCaptor;

  // función de inicio que se ejecuta ANTES DE cada test.
  @BeforeEach
  void setUp() {
    // preparar el service para el caso de prueba
    // que significa crear una nueva instancia
    // aquí la ventaja de usar constructor con todos los parámetros
    // en lugar de @Autowired
    // permite crear las clases para test más fácilmente

    // Otra alternativa es usar la anotación
    //@InjectMocks en el service para que mockito automáticamente genere
    // el constructor tal como viene aquí
    service = new CustomerServiceImpl(
        customerRepositoryMock, customerEntityToDtoConverterMock, customerDtoToEntityConverterMock);

    // los objetos anotados con @Mock y @Captor se reinician automáticamente
    // antes de cada test, no hace falta iniciarlos.
  }

  // Test Case
  // 1) documento existe en BD --> convierte a Dto y devuelve Dto
  @Test
  void testShouldReturnCustomerDtoWhenDocumentExistInDb() {
    // dato de prueba
    Customer c = Customer
        .builder()
        .document("CC-123")
        .name("juan")
        .address("xx")
        .phone("")
        .birthday(null)
        .build();
    // el llamado al reposotory debe devolver este valor de prueba
    // asumo que el llamado de customerRepository.findByDocument(document)
    // va a devolver un Customer y no me importa "como funciona por dentro"
    // esto significa que hago un mock del customerRepository para que devuelva ese valor
    Mockito.when(customerRepositoryMock.findByDocument(Mockito.anyString())).thenReturn(Optional.of(c));

    //Objeto esperado
    CustomerDto dtoResult = CustomerDto.builder()
        .name("Transformed Data")
        .build();
    Mockito.when(customerEntityToDtoConverterMock.convert(Mockito.any())).thenReturn(dtoResult);


    // ejecución del test
    var result = service.findCustomerByDocument("CC-999");

    // validar aserción de los valores esperados
    Assertions.assertNotNull(result);
    Assertions.assertEquals("Transformed Data", result.getName());

    // no interaction para los componentes que no se usan
    Mockito.verifyNoInteractions(customerDtoToEntityConverterMock);

    // verificar cuantas se llama una depencia
    Mockito.verify(customerRepositoryMock, Mockito.times(1)).findByDocument(Mockito.anyString());

  }

  // Test Case
  // 2) documento no existe en BD --> Lanza excepción
  @Test
  void shouldThrowNotFoundExceptionWhenDocumentNotFoundInDb() {
    // en este caso el mock para el repository es que
    // falle cuando se llama al método findByDocument
    // además la llamada del mock va a "capturar"
    // el valor que recibe como parámetro a través del objeto ArgumentCaptor
    Mockito.when(customerRepositoryMock.findByDocument(
        // captura el valor del parámetro apra validarlo
        // en un assert
        documentNumberCaptor.capture())).thenThrow(new CustomerNotFoundException("Test Error"));

    // ejecución del test
    // de una vez hacemos un AssertThrows porque el valor esperado es que arroje
    // excepción
    // en mi caso espeor que como mínimo lance una excepción de la jerarquía definida
    // así que con esperar la excepción base basta
    Exception e = Assertions.assertThrows(BaseInventoryApiException.class,
        () -> service.findCustomerByDocument("CC-999"));

    // aserciones adicionales

    // Espero que el parámetro recibico por
    // customerRepositoryMock.findByDocument
    // es el mismo documento que pasé a la llamada del servicio
    Assertions.assertEquals("CC-999", documentNumberCaptor.getValue());
    // espero que el mensaje de la excepción es el definido en el mock
    Assertions.assertEquals("Test Error", e.getMessage());

    // aserciones sobre las llamadas
    // Espero que el repository se llamó una sola vez
    Mockito.verify(customerRepositoryMock, Mockito.times(1)).findByDocument("CC-999");


    // espero que ningún convertidor se haya invocado
    // pues entró en el condicional de lanzar excepción
    Mockito.verifyNoInteractions(customerEntityToDtoConverterMock);
    Mockito.verifyNoInteractions(customerDtoToEntityConverterMock);
  }

}