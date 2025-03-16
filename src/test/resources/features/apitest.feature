
Feature: Consumo de la API FakeStore

  @Test1
  Scenario: Obtener todos los productos exitosamente
    When realizo un GET a "/products"
    Then el código de respuesta debe ser 200

