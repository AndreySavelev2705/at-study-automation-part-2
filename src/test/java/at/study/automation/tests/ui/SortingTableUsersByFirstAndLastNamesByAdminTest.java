package at.study.automation.tests.ui;

import at.study.automation.model.user.User;
import at.study.automation.ui.browser.BrowserUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static at.study.automation.utils.CompareUtils.*;
import static org.testng.Assert.*;

public class SortingTableUsersByFirstAndLastNamesByAdminTest extends BaseUiTest {
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
        User user5 = new User().create();

        openBrowser();
    }

    @Test
    public void sortingTableUsersByFirstAndLastNamesByAdminTest() {
        headerPage.loginButton.click();
        loginPage.login(admin);

        assertEquals(homePage.homePageHeader.getText(), "Домашняя страница");

        headerPage.administration.click();
        assertEquals(administrationPage.administrationHeader.getText(), "Администрирование");

        administrationPage.users.click();
        assertTrue(BrowserUtils.isElementPresent(userTablePage.usersTable));

        List<String> firstNames = BrowserUtils.getElementsText(userTablePage.usersFirstNames);
        assertFalse(isListSorted(firstNames));

        List<String> lastNames = BrowserUtils.getElementsText(userTablePage.usersLastNames);
        assertFalse(isListSorted(lastNames));

        userTablePage.button("Фамилия").click();
        userTablePage.button("Фамилия").click();
        lastNames = BrowserUtils.getElementsText(userTablePage.usersLastNames);
        assertListSortedByUserLastNameDesc(lastNames);

        userTablePage.button("Фамилия").click();
        lastNames = BrowserUtils.getElementsText(userTablePage.usersLastNames);
        assertListSortedByUserLastNameAsc(lastNames);

        userTablePage.button("Имя").click();
        userTablePage.button("Имя").click();
        firstNames = BrowserUtils.getElementsText(userTablePage.usersFirstNames);
        assertListSortedByUserFirstNameDesc(firstNames);

        userTablePage.button("Имя").click();
        firstNames = BrowserUtils.getElementsText(userTablePage.usersFirstNames);
        assertListSortedByUserFirstNameAsc(firstNames);
    }
}
