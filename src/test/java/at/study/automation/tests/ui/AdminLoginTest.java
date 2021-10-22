package at.study.automation.tests.ui;

import at.study.automation.model.user.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class AdminLoginTest extends BaseUiTest {
    private User admin;

    @BeforeMethod
    public void prepareFixtures() {
        admin = new User() {{
            setIsAdmin(true);
        }}.create();

        openBrowser();
    }

    @Test
    public void positiveAdminLoginTest() {
        assertEquals(homePage.homePage.getText(), "Домашняя страница");

        headerPage.loginButton.click();
        loginPage.login(admin);

        assertEquals(homePage.homePage.getText(), "Домашняя страница");
        assertEquals(headerPage.userActive.getText(), "Вошли как " + admin.getLogin());
        assertEquals(headerPage.homePage.getText(), "Домашняя страница");
        assertEquals(headerPage.myPage.getText(), "Моя страница");
        assertEquals(headerPage.projects.getText(), "Проекты");
        assertEquals(headerPage.help.getText(), "Помощь");
        assertEquals(headerPage.myAccount.getText(), "Моя учётная запись");
        assertEquals(headerPage.logout.getText(), "Выйти");
        assertEquals(headerPage.administration.getText(), "Администрирование");
        assertFalse(isElementPresent(headerPage.loginButton));
        assertFalse(isElementPresent(headerPage.register));
        assertTrue(isElementPresent(headerPage.search));
    }
}
