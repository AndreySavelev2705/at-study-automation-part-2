package at.study.automation.ui.pages;

import at.study.automation.ui.browser.BrowserManager;
import org.openqa.selenium.support.PageFactory;

public abstract class Page {

    Page() {
        PageFactory.initElements(BrowserManager.getBrowser().getDriver(), this);
    }
}
