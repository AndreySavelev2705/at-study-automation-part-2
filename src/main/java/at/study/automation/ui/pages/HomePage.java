package at.study.automation.ui.pages;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class HomePage extends Page {

    @FindBy(xpath = "//div[@id='main']//h2[text()='Домашняя страница']")
    public WebElement homePageHeader;
}
