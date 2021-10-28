package at.study.automation.ui.browser;

import at.study.automation.property.Property;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class BrowserUtils {

    public static List<String> getElementsText(List<WebElement> elements) {
        return elements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }

    public static boolean isElementPresent(WebElement webElement) {
        try {
            BrowserManager.getBrowser().getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        } finally {
            BrowserManager.getBrowser().getDriver().manage().timeouts().implicitlyWait(Property.getIntegerProperty("element.timeout"), TimeUnit.SECONDS);
        }
    }

    public static boolean isElementPresent(String projectName) {
        try {
            BrowserManager.getBrowser().getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
            WebElement webElement = BrowserManager.getBrowser().getDriver().findElement(By.xpath("//div[@id='projects-index']//a[text()='" + projectName + "']"));
            return webElement.isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        } finally {
            BrowserManager.getBrowser().getDriver().manage().timeouts().implicitlyWait(Property.getIntegerProperty("element.timeout"), TimeUnit.SECONDS);
        }
    }
}
