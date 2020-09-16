@regression
Feature: Adding Food in Food Delivery API

    # TODO: Write scenarios based on requirement and implement it.


     # TODO: REMOVE ALL Scenarios for students

  Background:
    Given reset cached food in Food Delivery API

  Scenario: Add new food to FoodDelivery
    Given add new food to FoodDelivery with the following fields
      | description | imageUrl        | price | name   | foodType  |
      | Wine        | https:foods.com | 20.00 | Merlot | Beverages |
    Then verify that status code is 200
    Then verify that food has been successfully added

  Scenario: Add new food to FoodDelivery without image url
    Given add new food to FoodDelivery without image url
      | description | price | name   | foodType  |
      | Wine        | 20.00 | Merlot | Beverages |
    Then verify that status code is 403
    Then verify response error message "Invalid request - Food image url cannot be null or empty."

  Scenario: Add new food to FoodDelivery without price
    Given add new food to FoodDelivery without price
      | description | imageUrl        | name   | foodType  |
      | Wine        | https:foods.com | Merlot | Beverages |
    Then verify that status code is 403
    Then verify response error message "Invalid request - Food price cannot be negative nor zero."

  Scenario: Add new food to FoodDelivery without name
    Given add new food to FoodDelivery without name
      | description | imageUrl        | price | foodType  |
      | Wine        | https:foods.com | 20.00 | Beverages |
    Then verify that status code is 403

  Scenario: Add new food to FoodDelivery without food type
    Given add new food to FoodDelivery without food type
      | description | imageUrl        | name   | price |
      | Wine        | https:foods.com | Merlot | 20.00 |
    Then verify that status code is 403
    Then verify response error message "Invalid request - Food type cannot be null or empty."

  Scenario: Add new food to FoodDelivery with invalid food type
    Given add new food to FoodDelivery with invalid food type
      | description | imageUrl        | name   | price | foodType |
      | Wine        | https:foods.com | Merlot | 20.00 | Soups    |
    Then verify that status code is 400
