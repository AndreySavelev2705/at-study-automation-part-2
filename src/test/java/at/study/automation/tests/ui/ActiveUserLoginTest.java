package at.study.automation.tests.ui;

import at.study.automation.model.user.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ActiveUserLoginTest extends BaseUiTest {

    private User activeUser;

    @BeforeMethod
    public void prepareFixtures() {
        activeUser = new User().create();

        openBrowser("/login");
    }

    @Test
    public void testActiveUserLogin() {
        loginPage.login(activeUser);

        assertEquals(headerPage.userActive.getText(), "Вошли как " + activeUser.getLogin());
        assertEquals(headerPage.homePage.getText(), "Домашняя страница");
        assertEquals(headerPage.myPage.getText(), "Моя страница");
        assertEquals(headerPage.projects.getText(), "Проекты");
        assertEquals(headerPage.help.getText(), "Помощь");
        assertEquals(headerPage.myAccount.getText(), "Моя учётная запись");
        assertEquals(headerPage.logout.getText(), "Выйти");
        assertFalse(isElementPresent(headerPage.administration));
        assertFalse(isElementPresent(headerPage.loginButton));
        assertFalse(isElementPresent(headerPage.register));
        assertTrue(isElementPresent(headerPage.search));
    }
}
