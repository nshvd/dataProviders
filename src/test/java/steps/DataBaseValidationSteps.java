package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import beans.CartItems;
import beans.Food;
import beans.Orders;
import org.junit.Assert;
import utils.beanutils.BeanHelper;
import utils.db.DataBaseUtils;

import java.sql.SQLException;
import java.util.*;

public class DataBaseValidationSteps {

           //TODO: IMPLEMENT HERE

    private String QUERY;
    private List<Map<String, Object>> databaseResult;

    @Given("^Establish a database connection$")
    public void establish_a_database_connection() throws Throwable {
        DataBaseUtils.connectToDatabase();
    }

    @Given("^remove all records from a \"([^\"]*)\" table$")
    public void removeAllRecordsFromATable(String table) throws Throwable {

        QUERY = "SET FOREIGN_KEY_CHECKS = 0;";
        DataBaseUtils.executeQuery(QUERY);
        QUERY = "TRUNCATE table " + table + ";";
        DataBaseUtils.executeQuery(QUERY);
        QUERY = "SET FOREIGN_KEY_CHECKS = 1;";
        DataBaseUtils.executeQuery(QUERY);
        databaseResult = DataBaseUtils.executeQuery("select * from " + table + ";");
        Assert.assertTrue("FAILED: Table is not empty", databaseResult.isEmpty());
    }


    @Given("^Add new data in the cart_items tables$")
    public void addNewDataInTheCart_itemsTables(List<CartItems> cartItems) throws SQLException {

        QUERY = "INSERT INTO cart_item VALUES(?, ?, ?, ?);";

        for (CartItems cart_item : cartItems) {
            DataBaseUtils.executeInsert(QUERY, cart_item, BeanHelper.getBeanPropertyNames(CartItems.class));
        }


    }

    @Then("^verify that new data has been inserted into the cart item table$")
    public void verifyThatNewDataHasBeenInsertedIntoTheTable(List<CartItems> items) throws SQLException {
        QUERY = "select * from cart_item order by id;";
        databaseResult = DataBaseUtils.executeQuery(QUERY);

        List<CartItems> cartItemsFromDB = DataBaseUtils.executeQueryToBean(CartItems.class, QUERY);

        List<CartItems> cartItems = new ArrayList<>(items);
        Collections.sort(cartItems);
        Collections.sort(cartItemsFromDB);

        if (cartItemsFromDB.size() == cartItems.size()) {
            Assert.assertTrue("Failed: Mismatch in data",cartItems.equals(cartItemsFromDB));
        } else {
            Assert.fail("Failed: Mismatch in lists size");
        }

    }

    @Given("^Add new data in the food tables$")
    public void addNewDataInTheFoodTables(List<Food> foods) throws SQLException {
        QUERY = "INSERT INTO food VALUES(?, ?, ?, ?, ?, ?);";

        for (Food food : foods) {
            DataBaseUtils.executeInsert(QUERY, food, BeanHelper.getBeanPropertyNames(Food.class));
        }

    }

    @Then("^verify that new data has been inserted into the food table$")
    public void verifyThatNewDataHasBeenInsertedIntoTheFoodTable(List<Food> foods) throws SQLException {
        QUERY = "select * from food order by id;";
        databaseResult = DataBaseUtils.executeQuery(QUERY);

        List<Food> expected = DataBaseUtils.executeQueryToBean(Food.class, QUERY);

        List<Food> actual = new ArrayList<>(foods);
        Collections.sort(actual);
        Collections.sort(expected);

        if (expected.size() == actual.size()) {
            Assert.assertTrue("Failed: Mismatch in data",actual.equals(expected));
        } else {
            Assert.fail("Failed: Mismatch in lists size");
        }
    }

    @Given("^Add new data in the orders tables$")
    public void addNewDataInTheOrdersTables(List<Orders> orders) throws SQLException {
         QUERY = "INSERT INTO orders VALUES(?, ?, ?, ?, ?);";

        for (Orders order : orders) {
            DataBaseUtils.executeInsert(QUERY, order, BeanHelper.getBeanPropertyNames(Orders.class));
        }

    }

    @Then("^verify that new data has been inserted into the orders table$")
    public void verifyThatNewDataHasBeenInsertedIntoTheOrdersTable(List<Orders> orders) throws SQLException {
        QUERY = "select * from orders order by id;";
        databaseResult = DataBaseUtils.executeQuery(QUERY);

        List<Orders> expected = DataBaseUtils.executeQueryToBean(Orders.class, QUERY);

        List<Orders> actual = new ArrayList<>(orders);
        Collections.sort(actual);
        Collections.sort(expected);

        if (expected.size() == actual.size()) {
            Assert.assertTrue("Failed: Mismatch in data",actual.equals(expected));
        } else {
            Assert.fail("Failed: Mismatch in lists size");
        }
    }

    @Given("^update price to \"([^\"]*)\" in food table for food id \"([^\"]*)\"$")
    public void updatePriceToInFoodTableForFoodId(Double price, int id) throws Throwable {
        QUERY = "UPDATE food SET price = ? WHERE id = ?;";
        DataBaseUtils.executeUpdate(QUERY,price,id);

        QUERY = "select * from food where id = "+id+";";

        List<Food> actual = DataBaseUtils.executeQueryToBean(Food.class, QUERY);
        for (Food food: actual) {
            if(food.getId()==id){
                Assert.assertEquals("Price did not match",price,food.getPrice());
                break;
            }
        }

    }

    @Given("^update order status to \"([^\"]*)\" in food table of order id \"([^\"]*)\"$")
    public void updateOrderStatusToInFoodTableOfOrderId(int status, int id) throws Throwable {
       QUERY = "UPDATE orders SET order_status = ? WHERE id = ? ;";

       DataBaseUtils.executeUpdate(QUERY,status,id);

        QUERY = "select * from orders where id = "+id+";";

        List<Orders> actual = DataBaseUtils.executeQueryToBean(Orders.class, QUERY);
        for (Orders orders: actual) {
            if(orders.getId()==id){
                Assert.assertEquals("Order status did not match",status,orders.getOrder_status().intValue());
                break;
            }
        }

    }

    @Given("^remove food record with food id \"([^\"]*)\"$")
    public void removeFoodRecordWithFoodId(int id) throws Throwable {
        QUERY = "SET FOREIGN_KEY_CHECKS = 0;";
        DataBaseUtils.executeQuery(QUERY);
        QUERY = "DELETE FROM food WHERE id=?;";
        DataBaseUtils.executeUpdate(QUERY,id);
        QUERY = "SET FOREIGN_KEY_CHECKS = 1;";
        DataBaseUtils.executeQuery(QUERY);

        QUERY = "select * from food where id = "+id+";";
        List<Food> actual = DataBaseUtils.executeQueryToBean(Food.class, QUERY);
        Assert.assertTrue(actual.isEmpty());
    }

    @Given("^remove order's records that was placed after \"([^\"]*)\"$")
    public void removeOrderSRecordsThatWasPlacedAfter(String arg0) throws Throwable {
        QUERY = "select * from orders where order_placed_at > "+arg0+";";
        List<Orders> orders = DataBaseUtils.executeQueryToBean(Orders.class, QUERY);
        Assert.assertFalse("Failed: Cannot perform delete operation on empty orders record",orders.isEmpty());
        QUERY = "DELETE FROM orders WHERE id=?;";

        for(Orders order:orders){
         DataBaseUtils.executeUpdate(QUERY,order.getId());
        }

        QUERY = "select * from orders where order_placed_at > "+arg0+";";
        List<Orders> deleted = DataBaseUtils.executeQueryToBean(Orders.class, QUERY);
        Assert.assertTrue("Failed: Delete operation was not successfull",deleted.isEmpty());

    }
}
