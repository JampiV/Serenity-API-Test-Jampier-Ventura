@Airlines
Feature: API aerolineas
  Como un usuario de la API de aerolíneas
  Quiero obtener la lista de todas las aerolíneas
  Para poder verificar los detalles de las aerolíneas disponibles

  @CP1
  Scenario: Obtener todas las aerolíneas exitosamente
    Given el actor establece el endpoint GET para obtener las aerolineas
    When el actor envia una solicitud GET
    Then el codigo de respuesta deberia ser 200

  @CP2
  Scenario Outline: Crear una aerolinea exitosamente
    Given el actor establece el endpoint POST para crear una aerolinea
    When el envia una solicitud HTTP POST con el "<_id>" "<name>" "<country>" "<logo>" "<slogan>" "<head_quaters>" "<website>" "<established>"
    Then el codigo de respuesta deberia ser 200
    Examples:
      | _id | name           | country | logo     | slogan                   | head_quaters | website     | established |
      | 1   | Ramon Castilla | Lima    | lima.png | Miraflores ciudad amable | Miraflores   | flowers.com | 1857        |

  @CP3
  Scenario Outline: Obtener aerolínea por id
    Given el actor establece el endpoint GET para obtener las aerolineas
    When el actor envia una solicitud GET incluyendo el "<_id>"
    Then el codigo de respuesta deberia ser 200
  Examples:
    | _id                                     |
    | 73dd5420-3bf9-48f3-a0b6-17cf7aa61b19    |
    | 459a01fe-57d4-4bc7-9692-3a4568bca4e6    |
    | 30cc4b9a-3276-4628-ae61-d6668eaa5114    |

  @CP4
  Scenario Outline: Actualizar nombre de pasajero
    Given el actor establece el endpoint PATCH para actualizar el nombre de un pasajero
    When el actor envia una solicitud PATCH incluyendo el id "<_id>" y el nombre "<name>"
    Then el codigo de respuesta deberia ser 200
  Examples:
    | _id                         | name                    |
    | 6691d5228393c0102e44b3f1    | Larry Cabirria          |
    | 6691d6618393c0ec3244b3f8    | Mauro Monzon            |
    | 6691dafe8393c06f7044b414    | Bruce Banner            |