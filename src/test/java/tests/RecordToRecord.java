package tests;

import beans.db.Food;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import utils.db.DataBaseUtils;
import utils.io_utils.CSVReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class RecordToRecord extends BaseTest{
    Food food;
    static List<Food> foodsFromFile = new ArrayList<>();

    @BeforeSuite
    public void setUp() throws IOException {
        CSVReader reader = new CSVReader("./foods.csv");
        String line;
        while ((line = reader.read()) != null){
            foodsFromFile.add(new Food(line));
        }
    }

    public RecordToRecord(){};

    @Factory(dataProvider = "DBObjProvider")
    public RecordToRecord(Food food){
        this.food = food;
    }

    @Test
    public void containsRecord(){
        MatcherAssert.assertThat("RECORD: \n" + food.toCSVString() + "\n", foodsFromFile.contains(food), Matchers.is(true));
    }

    @Test
    public void numOfRecordsTest(){
        MatcherAssert.assertThat(numOfRecords, Matchers.is(foodsFromFile.size()));
    }

}
