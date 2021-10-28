package at.study.automation.tests.ui;

import at.study.automation.model.user.User;
import at.study.automation.ui.browser.BrowserUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ActiveUserLoginTest extends BaseUiTest {

    private User activeUser;

    @BeforeMethod
    public void prepareFixtures() {
        activeUser = new User().create();

        openBrowser();
    }

    @Test
    public void testActiveUserLogin() {
        headerPage.loginButton.click();
        loginPage.login(activeUser);

        assertEquals(homePage.homePageHeader.getText(), "Домашняя страница");
        assertEquals(headerPage.userActive.getText(), "Вошли как " + activeUser.getLogin());
        assertEquals(headerPage.homePage.getText(), "Домашняя страница");
        assertEquals(headerPage.myPage.getText(), "Моя страница");
        assertEquals(headerPage.projects.getText(), "Проекты");
        assertEquals(headerPage.help.getText(), "Помощь");
        assertEquals(headerPage.myAccount.getText(), "Моя учётная запись");
        assertEquals(headerPage.logout.getText(), "Выйти");
        assertFalse(BrowserUtils.isElementPresent(headerPage.administration));
        assertFalse(BrowserUtils.isElementPresent(headerPage.loginButton));
        assertFalse(BrowserUtils.isElementPresent(headerPage.register));
        assertTrue(BrowserUtils.isElementPresent(headerPage.search));
    }
}
