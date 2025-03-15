Feature: Consumo de la API FakeStore


  Scenario: Obtener todos los productos exitosamente
    When realizo un GET a "/products"
    Then el código de respuesta debe ser 200

  Scenario: Crear un producto exitosamente
    When realizo un POST a "/products" con el JSON "create_product.json"
    Then el código de respuesta debe ser 200

  Scenario: Actualizar un producto
    When realizo un PUT a "/products/1" con el JSON "update_product.json"
    Then el código de respuesta debe ser 200

  Scenario: Eliminar un producto
    When realizo un DELETE a "/products/1"
    Then el código de respuesta debe ser 200