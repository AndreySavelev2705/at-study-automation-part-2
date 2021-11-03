package at.study.automation.tests.ui;

import at.study.automation.allure.AllureAssert;
import at.study.automation.model.user.User;
import at.study.automation.ui.browser.BrowserUtils;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AdminLoginTest extends BaseUiTest {
    private User admin;

    @BeforeMethod(description = "В системе заведен пользователь с праавами Администратора. Открыт браузер на главной странице.")
    public void prepareFixtures() {
        admin = new User() {{
            setIsAdmin(true);
        }}.create();

        openBrowser();
    }

    @Test (description = "Вход администратором. Проверка элемента \"Моя учетная запись\"")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Савельев Андрей Владимирович")
    public void positiveAdminLoginTest() {
        headerPage.loginButton.click();
        loginPage.login(admin);

        AllureAssert.assertEquals(
                homePage.homePageHeader.getText(),
                "Домашняя страница",
                "Текст элемента \"Домашняя страница\""
        );
        AllureAssert.assertEquals(
                headerPage.userActive.getText(),
                "Вошли как " + admin.getLogin(),
                "Текст элемента \"Вошли как \"" + admin.getLogin()
        );
        AllureAssert.assertEquals(
                headerPage.homePage.getText(),
                "Домашняя страница",
                "Текст элемента \"Домашняя страница\""
        );
        AllureAssert.assertEquals(
                headerPage.myPage.getText(),
                "Моя страница",
                "Текст элемента \"Моя страница\""
        );
        AllureAssert.assertEquals(
                headerPage.projects.getText(),
                "Проекты",
                "Текст элемента \"Проекты\""
                );
        AllureAssert.assertEquals(
                headerPage.help.getText(),
                "Помощь",
                "Текст элемента \"Помощь\""
        );
        AllureAssert.assertEquals(
                headerPage.myAccount.getText(),
                "Моя учётная запись",
                "Текст элемента \"Моя учетная запись\""
        );
        AllureAssert.assertEquals(
                headerPage.logout.getText(),
                "Выйти",
                "Текст элемента \"Выйти\""
        );
        AllureAssert.assertEquals(
                headerPage.administration.getText(),
                "Администрирование",
                "Текст элемента \"Администрирование\""
        );
        AllureAssert.assertFalse(
                BrowserUtils.isElementPresent(headerPage.loginButton),
                "Элемент не отображается"
        );
        AllureAssert.assertFalse(
                BrowserUtils.isElementPresent(headerPage.register),
                "Элемент не отображается"
        );
        AllureAssert.assertTrue(
                BrowserUtils.isElementPresent(headerPage.search),
                "Элемент отображается"
        );
    }
}
