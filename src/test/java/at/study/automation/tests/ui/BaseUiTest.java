package at.study.automation.tests.ui;

import at.study.automation.ui.browser.Browser;
import at.study.automation.ui.browser.BrowserManager;
import at.study.automation.ui.pages.HeaderPage;
import at.study.automation.ui.pages.HomePage;
import at.study.automation.ui.pages.LoginPage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;

public class BaseUiTest {
    protected Browser browser;
    protected HeaderPage headerPage;
    protected LoginPage loginPage;
    protected HomePage homePage;

    protected void openBrowser() {
        browser = BrowserManager.getBrowser();
        headerPage = new HeaderPage();
        loginPage = new LoginPage();
        homePage = new HomePage();
    }

    protected void openBrowser(String uri) {
        browser = BrowserManager.getBrowser(uri);
        headerPage = new HeaderPage();
        loginPage = new LoginPage();
    }

    @AfterMethod
    public void tearDown() {
        BrowserManager.closeBroser();
    }

    protected boolean isElementPresent(WebElement webElement) {
        try {
            webElement.isDisplayed();
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
