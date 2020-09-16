package steps;

import beans.restapi.Food;
import beans.restapi.CachedFoodResponse;
import com.google.gson.Gson;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import utils.db.DataBaseUtils;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import static utils.restapi.RestApiUtils.*;

//   TODO: Add your implementation here...


public class FoodDeliveryApiSteps {
    private Gson gson = new Gson();
    private Response response;
    private String jsonBody;
    List<Food> expectedFood;
    private Food food;
    static Logger logger = Logger.getLogger(FoodDeliveryApiSteps.class.getName());
    private List<Map<String, Object>> databaseResult;
    private String QUERY;
    private int numberOfFoodsInCache;

    @Given("^reset cached food in Food Delivery API$")
    public void resetCachedFoodInFoodDeliveryAPI() {
        response = requestSpecification()
                .and()
                .post("food/resetcache");
        logger.info("Resetting all cached food");
    }

    @Given("^add new food to FoodDelivery with the following fields$")
    public void addNewFoodToFoodDeliveryWithTheFollowingFields(List<Food> foods) {
        expectedFood = foods;
        for (Food food : foods) {
            jsonBody = gson.toJson(food);
            response = addFood(jsonBody);
        }

        logger.info("Adding a new food: " + foods);


    }

    @Then("^verify that status code is (\\d+)$")
    public void verify_that_status_code_is(int statusCode) throws Throwable {
        response.then().assertThat().statusCode(statusCode);
        logger.info("Verifying status code: " + statusCode);
    }

    @Then("^verify that food has been successfully added$")
    public void verifyThatFoodHasBeenSuccessfullyAdded() {

        List<Food> cachedFood = gson.fromJson(response.asString(), CachedFoodResponse.class).getFoodCached();
        Assert.assertEquals("Food do not match", expectedFood, cachedFood);
        logger.info("Food has been add to cache successfully");
    }


    @Given("^add new food to FoodDelivery without image url$")
    public void addNewFoodToFoodDeliveryWithoutImageUrl(List<Food> foods) {

        for (Food food : foods) {
            jsonBody = gson.toJson(food);
        }
        response = addFood(jsonBody);
        logger.info("Adding a new food: " + foods);

    }

    @Then("^verify response error message \"([^\"]*)\"$")
    public void verifyResponseErrorMessage(String arg0) throws Throwable {

        response.then().assertThat().body("errorMessage", Matchers.equalTo(arg0));
        logger.info("Unsuccessful request. Error: " + response.jsonPath().getString("errorMessage"));
    }

    @Given("^add new food to FoodDelivery without price$")
    public void addNewFoodToFoodDeliveryWithoutPrice(List<Food> foods) {

        for (Food food : foods) {
            jsonBody = gson.toJson(food);
        }
        response = addFood(jsonBody);
        logger.info("Adding a new food: " + foods);


    }

    @Given("^add new food to FoodDelivery without name$")
    public void addNewFoodToFoodDeliveryWithoutName(List<Food> foods) {
        for (Food food : foods) {
            jsonBody = gson.toJson(food);
        }
        response = addFood(jsonBody);
        logger.info("Adding a new food: " + foods);


    }

    @Given("^add new food to FoodDelivery without food type$")
    public void addNewFoodToFoodDeliveryWithoutFoodType(List<Food> foods) {
        for (Food food : foods) {
            jsonBody = gson.toJson(food);
        }
        response = addFood(jsonBody);
        logger.info("Adding a new food: " + foods);


    }

    @Given("^add new food to FoodDelivery with invalid food type$")
    public void addNewFoodToFoodDeliveryWithInvalidFoodType(List<Food> foods) {
        for (Food food : foods) {
            jsonBody = gson.toJson(food);
        }
        response = addFood(jsonBody);
        logger.info("Adding a new food: " + foods);

    }

    @Given("^user lists all food in cache$")
    public void userListsAllFoodInCache() {
        response = listCachedFood();
        logger.info("Listing all cached food");
    }

    @Then("^verify that response contains all cached foods$")
    public void verifyThatResponseContainsAllCachedFoods() {
        List<Food> cachedFood = gson.fromJson(response.asString(), CachedFoodResponse.class).getFoodCached();
        response.then().assertThat().body("numberOfFoodsInCache", Matchers.equalTo(cachedFood.size()));
        logger.info("Cached food size: " + cachedFood.size());
        logger.info("Number of Appetizers: " + response.jsonPath().getString("numberOfAppetizers"));
        logger.info("Number of Main dishes: " + response.jsonPath().getString("numberOfMainDishes"));
        logger.info("Number of Beverages: " + response.jsonPath().getString("numberOfUnknownFood"));

    }


    @Given("^user updates \"([^\"]*)\"'s price to \"([^\"]*)\"$")
    public void userUpdatesSPriceTo(String name, double priceValue) throws Throwable {
        for (Food foods : expectedFood) {
            food = foods;
        }
        food.setPrice(priceValue);
        jsonBody = gson.toJson(food);
        response = updateCachedFood(name, "price", jsonBody);
        logger.info("Updating " + name + "'s price to " + priceValue);
    }

    @Then("^verify that price have been updated$")
    public void verifyThatPriceHaveBeenUpdated() {

        List<Food> updatedFood = gson.fromJson(response.asString(), CachedFoodResponse.class).getFoodCached();
        Assert.assertEquals("Food do not match", expectedFood, updatedFood);
        logger.info("Food has been updated successfully");
    }

    @Given("^user saves all cached food$")
    public void userSavesAllCachedFood() {
        response = listCachedFood();
        numberOfFoodsInCache = response.jsonPath().getInt("numberOfFoodsInCache");
        expectedFood = gson.fromJson(response.asString(), CachedFoodResponse.class).getFoodCached();

        response = commitCachedFood();
        logger.info("Committing all cached food: " + numberOfFoodsInCache);
    }

    @Then("^verify number of saved food$")
    public void verifyNumberOfSavedFood() {
        response.then().assertThat().body("numberOfFoodsSaved", Matchers.equalTo(numberOfFoodsInCache));
        logger.info("Number of saved food in cache: " + numberOfFoodsInCache);
    }

    @Then("^verify response message \"([^\"]*)\"$")
    public void verifyResponseMessage(String expectedMessage) throws Throwable {
        response.then().assertThat().body("message", Matchers.equalTo(expectedMessage));
        logger.info("Message is displayed: " + expectedMessage);
    }

    @Then("^verify that all food information is saved in DB$")
    public void verifyThatAllFoodInformationIsSavedInDB() throws SQLException {
        QUERY = "select * from food order by id desc limit 1;";
        List<beans.db.Food> dbResult = DataBaseUtils.executeQueryToBean(beans.db.Food.class, QUERY);

        for (beans.db.Food dbFood : dbResult) {

            Assert.assertTrue("Food name do not match", expectedFood.stream().anyMatch(e -> e.getName().contains(dbFood.getName())));
            Assert.assertTrue("Food description do not match", expectedFood.stream().anyMatch(e -> e.getDescription().contains(dbFood.getDescription())));
            Assert.assertTrue("Food image url do not match", expectedFood.stream().anyMatch(e -> e.getImageUrl().contains(dbFood.getImage_url())));
            Assert.assertTrue("Food price do not match", expectedFood.stream().anyMatch(e -> e.getPrice() == dbFood.getPrice()));

        }
        logger.info("Comparing food data with data in database");
    }

    @Given("^user saves all cached food excluding \"([^\"]*)\"$")
    public void userSavesAllChachedFoodExcluding(String excludedFoodName) throws Throwable {
        response = commitCachedFood(excludedFoodName);
        logger.info("Committing cached food excluding " + excludedFoodName);
    }

}
