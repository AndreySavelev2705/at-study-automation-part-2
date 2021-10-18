package at.study.automation.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HeaderPage extends Page {
    @FindBy(xpath = "//div[@id='account']//a[@class='login']")
    public WebElement loginButton;
    @FindBy(xpath = "//div[@id='account']//a[@class='my-account']")
    public WebElement myAccount;

    public HeaderPage() {
        super();
    }
}
