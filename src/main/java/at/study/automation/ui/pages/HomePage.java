package at.study.automation.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends Page {

    @FindBy(xpath = "//div[@id='main']//h2[text()='Домашняя страница']")
    public WebElement homePage;

    public HomePage() {
        super();
    }
}
