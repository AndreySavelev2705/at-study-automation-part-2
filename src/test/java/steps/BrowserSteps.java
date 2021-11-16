package steps;

import at.study.automation.ui.browser.BrowserManager;
import cucumber.api.java.ru.Дано;

public class BrowserSteps {

    @Дано("Открыт браузер на странице \"(.+)\"")
    public void openBrowserOnPage(String url) {
        BrowserManager.getBrowser(url);
    }

    @Дано("Открыт браузер на главной странице")
    public void openBrowserOnPage() {
        BrowserManager.getBrowser();
    }
}
