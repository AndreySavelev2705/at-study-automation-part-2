package at.study.automation.ui.pages;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class HeaderPage extends Page {
    @FindBy(xpath = "//div[@id='account']//a[@class='login']")
    public WebElement loginButton;
    @FindBy(xpath = "//div[@id='account']//a[@class='my-account']")
    public WebElement myAccount;
    @FindBy(xpath = "//div[@id='top-menu']//a[@class='home']")
    public  WebElement homePage;
    @FindBy(xpath = "//div[@id='top-menu']//a[@class='my-page']")
    public WebElement myPage;
    @FindBy(xpath = "//div[@id='top-menu']//a[@class='projects']")
    public WebElement projects;
    @FindBy(xpath = "//div[@id='top-menu']//a[@class='help']")
    public WebElement help;
    @FindBy(xpath = "//div[@id='account']//a[@class='logout']")
    public WebElement logout;
    @FindBy(xpath = "//div[@id='loggedas']")
    public WebElement userActive;
    @FindBy(xpath = "//div[@id='account']//a[@class='register']")
    public WebElement register;
    @FindBy(xpath = "//div[@id='top-menu']//a[@class='administration']")
    public WebElement administration;
    @FindBy(xpath = "//div[@id='quick-search']//input[@class='small']")
    public WebElement search;
}
