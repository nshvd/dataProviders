Feature: Update cached food

    # TODO: Write scenarios based on requirement and implement it.


    # TODO: REMOVE ALL Scenarios for students
  Background:
    Given reset cached food in Food Delivery API
    And add new food to FoodDelivery with the following fields
      | description | imageUrl        | price | name         | foodType |
      | Steak       | https:foods.com | 50.00 | T-Bone steak | MainDish |
    Then verify that status code is 200
    Then verify that food has been successfully added

  Scenario: Update cached food
    Given user updates "T-Bone steak"'s price to "100.00"
    Then verify that status code is 200
    Then verify that price have been updated

  Scenario: Update price over the limit $125
    Given user updates "T-Bone steak"'s price to "125.50"
    Then verify that status code is 403
    Then verify response error message "Invalid request - Food price should be kept less than 125"
