
Feature: Consumo de la API FakeStore

  @Test1
  Scenario: Obtener todos los productos exitosamente
    When realizo un GET a "/products"
    Then el código de respuesta debe ser 200

  @Test2
  Scenario: Crear un producto exitosamente
    When realizo un POST a "/products" con el JSON "valid_product.json"
    Then el código de respuesta debe ser 200

  @Test3
  Scenario: Actualizar un producto
    When realizo un PUT a "/products/1" con el JSON "update_product.json"
    Then el código de respuesta debe ser 200

  @Test4
  Scenario: Eliminar un producto
    When realizo un DELETE a "/products/1"
    Then el código de respuesta debe ser 200