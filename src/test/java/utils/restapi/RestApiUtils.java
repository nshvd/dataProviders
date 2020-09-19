package utils.restapi;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static io.restassured.RestAssured.given;

public class RestApiUtils {

    public static final Logger LOGGER = LogManager.getLogger(RestApiUtils.class);

    /**
     * TODO:
     * Create a method that returns RequestSpecification
     * with the following headers: AcceptType,ContentType
     * @return RequestSpecification
     */
    public static RequestSpecification requestSpecification(){
       RequestSpecification requestSpec = RestAssured.given();
       requestSpec.contentType(ContentType.JSON);
       requestSpec.accept(ContentType.JSON);
       LOGGER.info("RequestSpecification was created.\n" +
               "AcceptType,ContentType - Json");
       return requestSpec;
    }

    /**
     * TODO:
     * Implement a addFood() method that returns a Response
     * @return Response
     */
    public static Response addFood(String jsonBody){
        return requestSpecification()
                .body(jsonBody)
                .post("food/cache/add");
    }

    /**
     * TODO:
     * Implement a listCachedFood() method that returns a Response
     * @return Response
     */
    public static Response listCachedFood(){
        return requestSpecification()
                .get("food/cache/list");
    }

    /**
     * TODO:
     * Implement a updateCachedFood() method that returns a Response
     * Method has the following arguments: String name, String fieldToUpdate,String jsonBody
     * @return Response
     */
    public static Response updateCachedFood(String name, String fieldToUpdate,String jsonBody){
        return requestSpecification()
                .queryParam("name", name)
                .queryParam("field", fieldToUpdate)
                .body(jsonBody)
                .put("food/cache/update");
    }

    /**
     * TODO:
     * Implement a commitCachedFood() method that returns a Response
     * @return Response
     */
    public static Response commitCachedFood(){
        return requestSpecification()
                .and()
                .post("food/commit");
    }

    /**
     * TODO:
     * Implement a commitCachedFood() method that returns a Response
     * Method has one parameter: String excludedFoodName
     * @return Response
     */
    public static Response commitCachedFood(String excludedFoodName){
        return requestSpecification()
                .queryParam("exclude", excludedFoodName)
                .post("food/commit");
    }

    public static Response resetCacheFoodAPI(){
        return requestSpecification().post("food/resetcache");
    }
}
