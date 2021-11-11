package at.study.automation.tests.ui;

import at.study.automation.model.user.User;
import at.study.automation.ui.browser.BrowserUtils;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static at.study.automation.allure.AllureAssert.*;
import static at.study.automation.ui.browser.BrowserUtils.click;
import static at.study.automation.utils.CompareUtils.*;

public class SortingTableUsersByFirstAndLastNamesByAdminTest extends BaseUiTest {
    private User admin;

    @BeforeMethod(description = "В системе заведен пользователь с правами администратора, " +
            "а также несколько пользователей без прав администратора. Открыт браузер на главной странице.")
    public void prepareFixtures() {
        admin = new User() {{
            setIsAdmin(true);
        }}.create();

        User user1 = new User().create();
        User user2 = new User().create();
        User user3 = new User().create();
        User user4 = new User().create();
        User user5 = new User().create();

        openBrowser();
    }

    @Test(description = "Администрирование. Сортировка списка пользователей по имени и фамилии")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Савельев Андрей Владимирович")
    public void sortingTableUsersByFirstAndLastNamesByAdminTest() {
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
        assertTrue(
                BrowserUtils.isElementPresent(userTablePage.usersTable),
                "Таблица с пользователями отображается"
        );

        List<String> firstNames = BrowserUtils.getElementsText(userTablePage.usersFirstNames);
        assertFalse(
                isListSorted(firstNames),
                "Таблица с пользователями не отсортирована по имени"
        );

        List<String> lastNames = BrowserUtils.getElementsText(userTablePage.usersLastNames);
        assertFalse(
                isListSorted(lastNames),
                "Таблица с пользователями не отсортирована по фамилии"
        );

        click(userTablePage.button("Фамилия"), "Фамилия: сортировка по возрастанию");
        click(userTablePage.button("Фамилия"), "Фамилия: сортировка по убыванию");

        lastNames = BrowserUtils.getElementsText(userTablePage.usersLastNames);
        assertListSortedByUserLastNameDesc(lastNames);

        click(userTablePage.button("Фамилия"), "Фамилия: сортировка по возрастанию");

        lastNames = BrowserUtils.getElementsText(userTablePage.usersLastNames);
        assertListSortedByUserLastNameAsc(lastNames);

        click(userTablePage.button("Имя"), "Имя: сортировка по возрастанию");
        click(userTablePage.button("Имя"), "Имя: сортировка по убыванию");

        firstNames = BrowserUtils.getElementsText(userTablePage.usersFirstNames);
        assertListSortedByUserFirstNameDesc(firstNames);

        click(userTablePage.button("Имя"), "Имя: сортировка по возрастанию");

        firstNames = BrowserUtils.getElementsText(userTablePage.usersFirstNames);
        assertListSortedByUserFirstNameAsc(firstNames);
    }
}
