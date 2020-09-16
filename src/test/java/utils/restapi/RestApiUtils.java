package utils.restapi;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class RestApiUtils {

    /**
     * TODO:
     * Create a method that returns RequestSpecification
     * with the following headers: AcceptType,ContentType
     * @return RequestSpecification
     */
    public static RequestSpecification requestSpecification(){
        return  given()
                .accept(ContentType.JSON)
                .and()
                .contentType(ContentType.JSON);
    }

    /**
     * TODO:
     * Implement a addFood() method that returns a Response
     * @return Response
     */
    public static Response addFood(String jsonBody){
        return requestSpecification()
                .and()
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
                .and()
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
                .and()
                .body(jsonBody)
                .queryParam("name",name)
                .queryParam("field",fieldToUpdate)
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
                .and()
                .queryParam("exclude",excludedFoodName)
                .post("food/commit");
    }
}
