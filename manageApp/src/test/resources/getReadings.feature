Feature: Getting sensor readings with filtering

  Scenario: Getting latest sensor reading from sensor
    When client calls the endpoint of the latest reading of sensor 0
    Then the client recieves a reply with code 200
    And receives the latest reading of sensor 0
    
  Scenario: Getting all sensor readings from sensor
    When client calls the endpoint of all the readings of sensor 0
    Then the client recieves a reply with code 200
    And receives all the readings of sensor 0
    
  Scenario: Getting all latest sensor readings
    When client calls the endpoint of all the latest readings
    Then the client recieves a reply with code 200
    And receives all the latest readings
    
  Scenario: Getting sensor info
    When client calls the endpoint with the info on sensor <id>
    Then the client recieves a reply with code 200
    And receives the info on sensor <id>
    
  Examples:
    | id  |
    | 0   |
    | 2   |
    | 4		|

