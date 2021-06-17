Feature: Message is consumed from Kafka and stored in database

  Scenario: Message exists on topic and is consumed and stored
    When Exists message in topic "<topic>"
    Then Message is received by consumer
    And Is stored in database

  Examples:
    | topic  |
    | temperature   |
    | humidity   |
    | co2		|
