package at.study.automation.tests.ui;

import at.study.automation.model.user.Status;
import at.study.automation.model.user.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class UnacceptedUserLoginTest extends BaseUiTest {

    private User unacceptedUser;

    @BeforeMethod
    public void prepareFixtures() {
        unacceptedUser = new User() {{
            setStatus(Status.UNACCEPTED);
        }}.create();

        openBrowser();
    }

    @Test
    public void testUnacceptedUserLogin() {
        assertEquals(homePage.homePage.getText(), "Домашняя страница");

        headerPage.loginButton.click();
        loginPage.login(unacceptedUser);


        assertEquals(loginPage.errorFlash.getText(), "Ваша учётная запись создана и ожидает подтверждения администратора.");
        assertFalse(isElementPresent(headerPage.myPage));
        assertEquals(headerPage.loginButton.getText(), "Войти");
        assertEquals(headerPage.register.getText(), "Регистрация");
    }
}
