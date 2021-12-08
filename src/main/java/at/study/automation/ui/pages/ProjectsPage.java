package at.study.automation.ui.pages;

import at.study.automation.cucumber.validators.annotations.ElementName;
import at.study.automation.cucumber.validators.annotations.PageName;
import at.study.automation.property.Property;
import at.study.automation.ui.browser.BrowserManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;
import java.util.concurrent.TimeUnit;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
@PageName("Проекты")
public class ProjectsPage extends Page {

    @ElementName("Список проектов")
    @FindBy(xpath = "//div[@id='projects-index']//li[@class='root']")
    public List<WebElement> projects;

    @ElementName("Применить")
    @FindBy(xpath = "//div[@id='query_form_with_buttons']//a[@class='icon icon-checked']")
    public WebElement submit;

    @ElementName("Проекты")
    @FindBy(xpath = "//div[@id='main']//h2[text()='Проекты']")
    public WebElement projectsLabel;

    public WebElement getProject(String projectName) {
        return BrowserManager.getBrowser()
                .getDriver()
                .findElement(By.xpath("//div[@id='projects-index']//a[text()='" + projectName + "']"));
    }

    public WebElement getProjectDescription(String projectDescriptionName) {
        return BrowserManager.getBrowser()
                .getDriver()
                .findElement(By.xpath("//div[@id='projects-index']//p[text()='" + projectDescriptionName + "']"));
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
