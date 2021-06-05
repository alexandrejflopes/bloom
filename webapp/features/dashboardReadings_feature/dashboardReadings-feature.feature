Feature: Dashboard Readings
  Scenario: temperature Readings.
    Given I am on the Dashboard page
    When the temperature readings area is present
    Then the values of temperature sensors must be visible

  Scenario: CO2 Readings.
    Given I am on the Dashboard page
    When the CO2 readings area is present
    Then the values of CO2 sensors must be visible

  Scenario: Humidity Readings.
    Given I am on the Dashboard page
    When the plant tray are present
    And its humidity reading area is present
    Then the value of that humidity sensor must be visible