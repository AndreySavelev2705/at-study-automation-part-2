package at.study.automation.tests.ui;

import at.study.automation.model.user.User;
import at.study.automation.ui.browser.BrowserUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static at.study.automation.utils.CompareUtils.assertListSortedByUserNameAsc;
import static at.study.automation.utils.CompareUtils.assertListSortedByUserNameDesc;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class UserTableUsersNamesSortingByAdminTest extends BaseUiTest {
    private User admin;

    @BeforeMethod
    public void prepareFixtures() {
        admin = new User(){{
            setIsAdmin(true);
        }}.create();

        User user1 = new User().create();
        User user2 = new User().create();
        User user3 = new User().create();
        User user4 = new User().create();

        openBrowser();
    }

    @Test
    public void userTableUserNamessSortingByAdminTest() {
        headerPage.loginButton.click();

        loginPage.login(admin);
        assertEquals(homePage.homePageHeader.getText(), "Домашняя страница");

        headerPage.administration.click();
        assertEquals(administrationPage.administrationHeader.getText(), "Администрирование");

        administrationPage.users.click();
        assertTrue(BrowserUtils.isElementPresent(userTablePage.usersTable));

        userTablePage.button("Пользователь").click();
        List<String> usersByDesc = BrowserUtils.getElementsText(userTablePage.usersLogins);
        assertListSortedByUserNameDesc(usersByDesc);

        userTablePage.button("Пользователь").click();
        List<String> usersByAsc = BrowserUtils.getElementsText(userTablePage.usersLogins);
        assertListSortedByUserNameAsc(usersByAsc);
    }
}
