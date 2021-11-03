package at.study.automation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleMethodTest {

    @Test
    public void positiveTest() {

    }

    @Test
    public void assertFailTest() {
        Assert.assertEquals(5, 3);
    }

    @Test
    public void throwExceptionTest() {
        throw new IllegalArgumentException("Мое исключение");
    }
}
