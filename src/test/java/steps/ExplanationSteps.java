package steps;

import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.Пусть;
import cucumber.api.java.ru.Тогда;
import org.testng.Assert;

public class ExplanationSteps {
    int result;

    @Дано("Я складываю (.+) и (.+)")
    public void sum(int first, int second) {
        result = first + second;
    }

    @Пусть("Я вычитаю из числа (.+) число (.+)")
    public void subtract(int first, int second) {
        result = first - second;
    }

    @Тогда("В результате я получаю (.+)")
    public void assertResult(int expectedResult) {
        Assert.assertEquals(result , expectedResult);
    }
}
