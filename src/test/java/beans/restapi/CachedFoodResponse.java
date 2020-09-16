package beans.restapi;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * TODO:
 * Add following fields to Food pojo class:
 *    Fields:
 *   -  List of Food object: foodCached
 *
 * Implement getters and setters.
 * */

@Data
public class CachedFoodResponse {

    private List<Food> foodCached;

}
