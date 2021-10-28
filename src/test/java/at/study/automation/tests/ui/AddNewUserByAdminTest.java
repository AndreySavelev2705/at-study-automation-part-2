package at.study.automation.tests.ui;

import at.study.automation.db.requests.UserRequests;
import at.study.automation.model.user.User;
import at.study.automation.utils.StringUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class AddNewUserByAdminTest extends BaseUiTest {

    private User admin;

    @BeforeMethod
    public void prepareFixtures() {
        admin = new User(){{
            setIsAdmin(true);
        }}.create();

        openBrowser();
    }

    @Test
    public void addNewUserByAdminTest() {
        headerPage.loginButton.click();

        loginPage.login(admin);
        assertEquals(homePage.homePageHeader.getText(), "Домашняя страница");

        headerPage.administration.click();
        assertEquals(administrationPage.administrationHeader.getText(), "Администрирование");

        administrationPage.users.click();

        userTablePage.addNewUser.click();
        assertEquals(addNewUser.breadCrumbs.getText(), "Пользователи » Новый пользователь");

        User userFroCreating = new User();

        addNewUser.userLogin.sendKeys(userFroCreating.getLogin());
        addNewUser.userFirstName.sendKeys(userFroCreating.getFirstName());
        addNewUser.userLastName.sendKeys(userFroCreating.getLastName());
        addNewUser.userMail.sendKeys(StringUtils.randomEmail());
        addNewUser.generatePassword.click();
        addNewUser.create.click();
        assertEquals(addNewUser.flashNotice.getText(), "Пользователь " + userFroCreating.getLogin() + " создан.");

        User newUser = new UserRequests().read(userFroCreating.getLogin());
        assertEquals(newUser.getLogin(), userFroCreating.getLogin());
    }
}
