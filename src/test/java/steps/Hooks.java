package steps;

import cucumber.api.java.Before;
import io.restassured.RestAssured;
import utils.config.Config;
import utils.db.DataBaseUtils;

public class Hooks {

    //Implement DB Connection Steps here
    private static boolean isExecuted = false;

    @Before
    public void testExecutionSetup() throws Exception {
        if (!isExecuted) {
            DataBaseUtils.connectToDatabase();
            RestAssured.baseURI = Config.getPropertiesValue("food_delivery_base_url");
            isExecuted = true;
        }
    }
}
