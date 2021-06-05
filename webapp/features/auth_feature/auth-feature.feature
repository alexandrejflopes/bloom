Feature: Authentication
  Scenario: Bloom Login.
    Given I am on the Login page
    When I insert my e-mail "antonio@ua.pt"
    And I hit entrar
    Then the page title is "Estado da estufa de antonio@ua.pt"