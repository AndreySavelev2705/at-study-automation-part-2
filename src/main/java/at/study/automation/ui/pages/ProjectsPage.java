package at.study.automation.ui.pages;

import at.study.automation.ui.browser.BrowserManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ProjectsPage extends Page {

    @FindBy(xpath = "//div[@id='projects-index']//li[@class='root']")
    public List<WebElement> projects;
    @FindBy(xpath = "//div[@id='query_form_with_buttons']//a[@class='icon icon-checked']")
    public WebElement submit;
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
}
