package at.study.automation.ui.pages;

import at.study.automation.ui.browser.BrowserManager;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ProjectFilters extends Page {

    @FindBy(xpath = "//table[@id='filters-table']/tbody/tr[@class='filter']/td[@class='operator']/select[@id='operators_status']/option")
    public List<WebElement> operatorsStatus;

    @FindBy(xpath = "//table[@id='filters-table']/tbody/tr[@class='filter']/td[@class='values']//select[@class='value']/option")
    public List<WebElement> valuesStatus;


    public WebElement button(String text) {
        return BrowserManager.getBrowser()
                .getDriver()
                .findElement(By.xpath("//table[@id='filters-table']/tbody/tr[@class='filter']/td[@class='values']//select[@class='value']/option[text()='" + text + "']"));
    }
}
