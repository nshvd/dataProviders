package tests;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.ITestContext;
import org.testng.ITestNGMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

public class FoodFileTest extends BaseTest{
    private String[] line;
    private static Set<Integer> ids = new HashSet<>();


    public FoodFileTest(){};

    @Factory(dataProvider = "fileProvider")
    public FoodFileTest(String line){
        this.line = line.split(",");
    }

    @Test(description = "Verify that the price value is numeric")
    public void verifyPriceIsNumeric(){
       try{
           Double.parseDouble(line[5]);
       }catch (RuntimeException e){
           MatcherAssert.assertThat(false, Matchers.is(true));
       }
    }

    @Test(description = "Verify that all ids are unique")
    public void verifyUniqueness(){
        int id;
        try{
             id = Integer.parseInt(line[0]);
             MatcherAssert.assertThat(ids.contains(id), Matchers.is(false));
             ids.add(id);
        }catch (RuntimeException e){
            MatcherAssert.assertThat(false, Matchers.is(true));
        }
    }

    @Test(description = "Verify that type value is numeric")
    public void verifyType(){
        try{
            Integer.parseInt(line[2]);
        }catch (RuntimeException e){
            MatcherAssert.assertThat(false, Matchers.is(true));
        }
    }
}
