package at.study.automation.tests.ui;

import at.study.automation.db.requests.UserRequests;
import at.study.automation.model.user.User;
import at.study.automation.utils.StringUtils;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static at.study.automation.allure.AllureAssert.assertEquals;
import static at.study.automation.ui.browser.BrowserUtils.*;

public class CreateNewUserPageByAdminTest extends BaseUiTest {

    private User admin;

    @BeforeMethod(description = "В системе заведен пользователь с прапвами администратора. Открыт браузер на главной странице.")
    public void prepareFixtures() {
        admin = new User() {{
            setIsAdmin(true);
        }}.create();

        openBrowser();
    }

    @Test(description = "Администрирование. Создание пользователя.")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Савельев Андрей Владимирович")
    public void addNewUserByAdminTest() {
        click(headerPage.loginButton, "Войти");

        loginPage.login(admin);
        assertEquals(
                homePage.homePageHeader.getText(),
                "Домашняя страница",
                "Текст элемента \"Домашняя страница\""
        );

        click(headerPage.administration, "Администрирование");
        assertEquals(
                administrationPage.administrationHeader.getText(),
                "Администрирование",
                "Текст элемента \"Администрирование\""
        );

        click(administrationPage.users, "Пользователи");

        click(userTablePage.addNewUser, "Новый пользователь");
        assertEquals(
                createNewUserPage.breadCrumbs.getText(),
                "Пользователи » Новый пользователь",
                "Структура хлебных крошек"
        );

        User userFroCreating = new User();

        sendKeys(createNewUserPage.userLogin, userFroCreating.getLogin(), "Пользователь");
        sendKeys(createNewUserPage.userFirstName, userFroCreating.getFirstName(), "Имя");
        sendKeys(createNewUserPage.userLastName, userFroCreating.getLastName(), "Фамилия");
        sendKeys(createNewUserPage.userMail, StringUtils.randomEmail(), "Email");
        click(createNewUserPage.generatePassword, "Создание пароля");
        click(createNewUserPage.create, "Создать");

        assertEquals(
                createNewUserPage.flashNotice.getText(),
                "Пользователь " + userFroCreating.getLogin() + " создан.",
                "Сообщение \"Пользователь создан\""
        );

        User newUser = new UserRequests().read(userFroCreating.getLogin());
        assertEquals(
                newUser.getLogin(),
                userFroCreating.getLogin(),
                "Проверка, что пользователь создан в бд"
        );
    }
}
