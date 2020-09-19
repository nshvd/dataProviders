package tests;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import java.util.regex.Matcher;


public class DPTest1 extends BaseTest {

    @Test(dataProvider = "dp for test1")
    public void t1(String s1){
        MatcherAssert.assertThat(s1 != null, Matchers.is(true));
        MatcherAssert.assertThat(s1, Matchers.not(""));
        MatcherAssert.assertThat(s1.length() < 100, Matchers.is(true));
    }

    @Test(dataProvider = "dp multidim")
    public void t2(String name, Integer age){
        MatcherAssert.assertThat(name.length() < 100, Matchers.is(true));
        MatcherAssert.assertThat(age > 10 && age < 100, Matchers.is(true));
    }
}
