package tests;

import beans.db.Food;
import org.testng.annotations.DataProvider;
import utils.db.DataBaseUtils;
import utils.io_utils.CSVReader;
import utils.io_utils.CSVWriter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BaseTest {
    static int numOfRecords = 0;

    @DataProvider(name = "dp for test1")
    public Object[] dp1(){
        return new Object[]{"Input1", "Input2", "Input3"};
    }

    @DataProvider(name = "dp multidim")
    public Object[][] dp2(){
        //{ {"John", 23} , {"Jane", 29} , {"James", 24} }
        Object [][] arr = new Object[3][];
        arr[0] = new Object[]{"John", 23};
        arr[1] = new Object[]{"Jane", 29};
        arr[2] = new Object[]{"James", -1};
        return arr;
    }

    @DataProvider()
    public Iterator<Object> fileProvider() throws IOException {
        CSVReader reader = new CSVReader("./foods.csv");
        List<Object> lines = new ArrayList<>();
        String line;
        while ((line = reader.read()) != null){
            lines.add(line);
        }
        return lines.iterator();
    }

    // REST API POST request
    // BODY {item details, state, customer details}
    // DataProviders will return 10 objects (each will contain) {item details, state, customer details}
    // Test method will run 10 times
    // if(isTaxExempt(customer)) Assert.assertTrue(salesTax == 0, "");

    @DataProvider
    public Iterator<Object> DBObjProvider() throws Exception {
        DataBaseUtils.connectToDatabase();
        List<Object> values = new ArrayList<>(Food.getAll());
        numOfRecords = values.size();
        return values.iterator();
    }
}
