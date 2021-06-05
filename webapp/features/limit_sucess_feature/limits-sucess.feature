Feature: Set limits.
  Scenario: Set temperature and humidity limits with success.
    Given I am on the Settings page
    When I insert humidity minimum limit "400"
    And I insert humidity maximum limit "700"
    And I change to temperature tab
    And I insert temperature minimum limit "15"
    And I insert temperature maximum limit "25"
    And I hit Guardar
    Then I see a success dialog box saying "Sucesso! Novos limites de humidade e temperatura salvos."