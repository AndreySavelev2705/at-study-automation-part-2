package at.study.automation.tests.ui;

import at.study.automation.ui.browser.Browser;
import at.study.automation.ui.browser.BrowserManager;
import at.study.automation.ui.pages.*;
import at.study.automation.ui.pages.ProjectsPage;
import io.qameta.allure.Step;
import org.testng.annotations.AfterMethod;

public class BaseUiTest {
    protected Browser browser;
    protected HeaderPage headerPage;
    protected LoginPage loginPage;
    protected HomePage homePage;
    protected AdministrationPage administrationPage;
    protected UserTablePage userTablePage;
    protected ProjectsPage projectsPage;
    protected ProjectFilters projectFilters;
    protected AddNewUser addNewUser;

    @Step("Открыт браузер на главной странице")
    protected void openBrowser() {
        browser = BrowserManager.getBrowser();
        initPages();
    }

    @Step("Открыт браузер на странице {0}")
    protected void openBrowser(String uri) {
        browser = BrowserManager.getBrowser(uri);
        initPages();
    }

    private void initPages() {
        headerPage = Page.getPages(HeaderPage.class);
        loginPage = Page.getPages(LoginPage.class);
        homePage = Page.getPages(HomePage.class);
        administrationPage = Page.getPages(AdministrationPage.class);
        userTablePage = Page.getPages(UserTablePage.class);
        projectsPage = Page.getPages(ProjectsPage.class);
        projectFilters = Page.getPages(ProjectFilters.class);
        addNewUser = Page.getPages(AddNewUser.class);
    }

    @AfterMethod(description = "Закрытие браузера")
    public void tearDown() {
        BrowserManager.closeBrowser();
    }
}
