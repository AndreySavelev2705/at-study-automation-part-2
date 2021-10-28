package at.study.automation.ui.pages;

import at.study.automation.ui.browser.BrowserManager;
import lombok.SneakyThrows;
import org.openqa.selenium.support.PageFactory;

public abstract class Page {

    @SneakyThrows
    public static <T extends Page> T getPages(Class<T> clazz) {
        T page = clazz.getDeclaredConstructor().newInstance();
        PageFactory.initElements(BrowserManager.getBrowser().getDriver(), page);
        return page;
    }
}
