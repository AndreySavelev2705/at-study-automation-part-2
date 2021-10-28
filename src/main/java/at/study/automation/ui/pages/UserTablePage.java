package at.study.automation.ui.pages;

import at.study.automation.ui.browser.BrowserManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class UserTablePage extends Page {

    @FindBy(xpath = "//table[@class='list users']/tbody//td[@class='created_on']")
    public List<WebElement> creationDates;
    @FindBy(xpath = "//div[@class='autoscroll']/table[@class='list users']")
    public WebElement usersTable;
    @FindBy(xpath = "//table[@class='list users']/tbody//td[@class='username']")
    public List<WebElement> usersLogins;
    @FindBy(xpath = "//table[@class='list users']/tbody//td[@class='firstname']")
    public List<WebElement> usersFirstNames;
    @FindBy(xpath = "//table[@class='list users']/tbody//td[@class='lastname']")
    public List<WebElement> usersLastNames;
    @FindBy(xpath = "//div[@id='content']//a[@class='icon icon-add']")
    public WebElement addNewUser;

    public WebElement button(String text) {
        return BrowserManager.getBrowser().getDriver().findElement(By.xpath("//table[@class='list users']/thead//th[.='" + text + "']"));
    }
}
