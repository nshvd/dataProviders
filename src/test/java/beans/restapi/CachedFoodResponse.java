package beans.restapi;


import lombok.Data;

import java.util.List;

/**
 * TODO:
 * Add following fields to Food pojo class:
 *    Fields:
 *   -  List of Food object: foodCached
 *
 * Implement getters and setters.
 *
 * "numberOfFoodsInCache": 0,
 *     "numberOfAppetizers": 0,
 *     "numberOfMainDishes": 0,
 *     "numberOfUnknownFood": 0,
 *     "foodCached": []
 * */

@Data
public class CachedFoodResponse {

    private int numberOfFoodsInCache;
    private int numberOfAppetizers;
    private int numberOfMainDishes;
    private int numberOfUnknownFood;
    private List<Food> foodCached;
}
