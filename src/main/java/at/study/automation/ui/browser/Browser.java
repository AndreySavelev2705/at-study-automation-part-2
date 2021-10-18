package at.study.automation.ui.browser;

import at.study.automation.property.Property;
import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.util.concurrent.TimeUnit.SECONDS;

@Getter
public class Browser {

    private final WebDriver driver;
    private final WebDriverWait wait;

    Browser() {
        this("");
    }

    Browser(String uri) {
        driver = DriverFactory.getDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        wait = new WebDriverWait(driver, 10);
        get(uri);
    }

    /**
     * Метод позволяет получить полный uri необходимого для доступа к какому-либо
     * ресурсу приложения
     *
     * @param uri - уникальный эндпоинт для доступа какому-либо ресурсу приложения
     */
    public void get(String uri) {
        getDriver().get(Property.getStringProperty("url") + uri);
    }

    /**
     * Метод позволяет обновить страницу браузера
     *
     */
    public void refresh() {
        getDriver().navigate().refresh();
    }
}
