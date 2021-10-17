package at.study.automation.tests.ui;

import at.study.automation.model.user.User;
import at.study.automation.property.Property;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdminLoginTest {
    private User admin;

    @BeforeMethod
    public void prepareFixtures() {
        admin = new User() {{
            setIsAdmin(true);
        }}.create();
    }

    @Test
    public void positiveAdminLoginTest() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get(Property.getStringProperty("url"));

        WebElement loginButton = driver.findElement(By.id("account"))
                .findElement(By.className("login"));
        loginButton.click();

        WebElement loginInput = driver.findElement(By.xpath("//input[@id='username']"));
        loginInput.sendKeys(admin.getLogin());

        WebElement passwordInput = driver.findElement(By.xpath("//input[@id='password']"));
        passwordInput.sendKeys(admin.getPassword());

        WebElement singInButton = driver.findElement(By.xpath("//input[@id='login-submit']"));
        singInButton.click();

        Thread.sleep(3000);

        WebElement myAccount = driver.findElement(By.xpath("//div[@id='account']//a[@class='my-account']"));

        Assert.assertEquals(myAccount.getText(), "Моя учётная запись");

        driver.quit();
    }
}
