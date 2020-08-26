Feature:

  Background: Establish a database connection
    Given Establish a database connection

  Scenario Outline: Remove all records from tables
    Given remove all records from a "<table_name>" table
    Examples:
      | table_name |
      | cart_item  |
      | food       |
      | orders     |

  Scenario: Add new data into the food tables
    Given Add new data in the food tables
      | id | description | food_type | image_url                               | name        | price |
      | 1  | appetizers  | 2         | https://www.fooddelivery.com/appetizers | hummus      | 12.99 |
      | 2  | salads      | 3         | https://www.fooddelivery.com/salads     | greek salad | 21.00 |
      | 3  | soups       | 5         | https://www.fooddelivery.com/soups      | tomato soup | 5.50  |
    Then verify that new data has been inserted into the food table
      | id | description | food_type | image_url                               | name        | price |
      | 1  | appetizers  | 2         | https://www.fooddelivery.com/appetizers | hummus      | 12.99 |
      | 2  | salads      | 3         | https://www.fooddelivery.com/salads     | greek salad | 21.00 |
      | 3  | soups       | 5         | https://www.fooddelivery.com/soups      | tomato soup | 5.50  |


  Scenario: Add new data into the cart_items tables
    Given Add new data in the cart_items tables
      | id | quantity | total_price | food_id |
      | 1  | 12       | 29.99       | 1       |
      | 2  | 2        | 219.09      | 1       |
      | 3  | 43       | 100.00      | 2       |
      | 4  | 13       | 76.32       | 1       |
      | 5  | 4        | 34.55       | 3       |
    Then verify that new data has been inserted into the cart item table
      | id | quantity | total_price | food_id |
      | 1  | 12       | 29.99       | 1       |
      | 2  | 2        | 219.09      | 1       |
      | 3  | 43       | 100.00      | 2       |
      | 4  | 13       | 76.32       | 1       |
      | 5  | 4        | 34.55       | 3       |

  Scenario: Add new data into the orders tables
    Given Add new data in the orders tables
      | id | order_placed_at     | order_status | order_updated_at    | custom_user_id |
      | 1  | 2020-01-12 12:20:32 | 2            | 2020-01-12 12:40:01 | 1              |
      | 2  | 2020-01-12 10:00:01 | 1            | 2020-01-12 11:00:00 | 2              |
      | 3  | 2020-01-12 09:30:21 | 2            | 2020-01-12 10:22:00 | 1              |
    Then verify that new data has been inserted into the orders table
      | id | order_placed_at     | order_status | order_updated_at    | custom_user_id |
      | 1  | 2020-01-12 12:20:32 | 2            | 2020-01-12 12:40:01 | 1              |
      | 2  | 2020-01-12 10:00:01 | 1            | 2020-01-12 11:00:00 | 2              |
      | 3  | 2020-01-12 09:30:21 | 2            | 2020-01-12 10:22:00 | 1              |

  Scenario Outline: Update price in food table
    Given update price to "<price>" in food table for food id "<food_id>"
    Examples:
      | food_id | price |
      | 1       | 28.98 |

  Scenario Outline: Update order status in orders table
    Given update order status to "<status>" in food table of order id "<order_id>"
    Examples:
      | order_id | status |
      | 2        | 1      |

  Scenario Outline: Remove food record in food table
    Given remove food record with food id "<id>"
    Examples:
      | id |
      | 2  |

    Scenario: Remove orders record in orders table
    Given remove order's records that was placed after "2020-01-12"

