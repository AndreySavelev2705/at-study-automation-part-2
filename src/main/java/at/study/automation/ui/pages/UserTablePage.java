package at.study.automation.ui.pages;

import at.study.automation.cucumber.validators.annotations.ElementName;
import at.study.automation.cucumber.validators.annotations.PageName;
import at.study.automation.ui.browser.BrowserManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@PageName("Пользователи")
public class UserTablePage extends Page {

    @ElementName("Даты создания")
    @FindBy(xpath = "//table[@class='list users']/tbody//td[@class='created_on']")
    public List<WebElement> creationDates;

    @ElementName("Пользователи")
    @FindBy(xpath = "//div[@class='autoscroll']/table[@class='list users']")
    public WebElement usersTable;

    @ElementName("Логины")
    @FindBy(xpath = "//table[@class='list users']/tbody//td[@class='username']")
    public List<WebElement> usersLogins;

    @ElementName("Имена")
    @FindBy(xpath = "//table[@class='list users']/tbody//td[@class='firstname']")
    public List<WebElement> usersFirstNames;

    @ElementName("Фамилии")
    @FindBy(xpath = "//table[@class='list users']/tbody//td[@class='lastname']")
    public List<WebElement> usersLastNames;

    @ElementName("Новый пользователь")
    @FindBy(xpath = "//div[@id='content']//a[@class='icon icon-add']")
    public WebElement addNewUser;

    public WebElement button(String text) {
        return BrowserManager.getBrowser().getDriver().findElement(By.xpath("//table[@class='list users']/thead//th[.='" + text + "']"));
    }
}