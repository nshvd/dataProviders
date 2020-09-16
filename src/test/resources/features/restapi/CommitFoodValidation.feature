Feature: Commit cached food and save it in DB

  # TODO: Write scenarios based on requirement and implement it.


 # TODO: REMOVE ALL Scenarios for students

  Background:
    Given reset cached food in Food Delivery API
    And add new food to FoodDelivery with the following fields
      | description | imageUrl        | price | name  | foodType |
      | Noodles     | https:foods.com | 10.00 | Ramen | MainDish |
    Then verify that status code is 200
    Then verify that food has been successfully added

  Scenario: Save all cached food
    Given user saves all cached food
    Then verify that status code is 200
    Then verify number of saved food
    Then verify response message "Food Cache is committed to db"
    Then verify that all food information is saved in DB

  Scenario: Save cached food excluding one food
    Given user saves all cached food excluding "Diet Coke"
    Then verify that status code is 200
    Then verify response message "Food Cache is committed to db"
    Then verify that all food information is saved in DB