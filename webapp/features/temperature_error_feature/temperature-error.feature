Feature: Set limits with errors.
  Scenario: Set temperature above the limit.
    Given I am on the Settings page
    When I insert humidity minimum limit "300"
    And I insert humidity maximum limit "600"
    And I change to temperature tab
    And I insert temperature minimum limit "15"
    And I insert temperature maximum limit "35"
    And I hit Guardar
    Then I see a success dialog box saying "A temperatura deve encontrar-se entre 0ºC e 30ºC."