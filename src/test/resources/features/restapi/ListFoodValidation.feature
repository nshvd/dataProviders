Feature: List food validation

  # TODO: Write scenarios based on requirement and implement it.


  # TODO: REMOVE ALL Scenarios for students
  Background:

    Given reset cached food in Food Delivery API
    And add new food to FoodDelivery with the following fields
      | description | imageUrl        | price | name   | foodType  |
      | Wine        | https:foods.com | 20.00 | Merlot | Beverages |
    Then verify that status code is 200
    Then verify that food has been successfully added

  Scenario: List all food in cache

    Given user lists all food in cache
    Then verify that status code is 200
    Then verify that response contains all cached foods
