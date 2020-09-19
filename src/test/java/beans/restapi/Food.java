package beans.restapi;


import lombok.Data;

/**
 * TODO:
 * Add following fields to Food pojo class:
 *    Fields:
 *  - description
 *  - imageUrl
 *  - price
 *  - name
 *  - foodType
 * Implement getters and setters.
 *
 * "description": "Merlot",
 *             "imageUrl": "https:foods.com",
 *             "price": 20.00,
 *             "name": "Merlot",
 *             "foodType": "Beverages"
 * */

@Data
public class Food implements Comparable<Food> {

    private String description;
    private String imageUrl;
    private double price;
    private String name;
    private String foodType;

    @Override
    public int compareTo(Food food) {
        return this.description.compareTo(food.description);
    }
}
