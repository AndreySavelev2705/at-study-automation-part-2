package steps;

import at.study.automation.context.Context;
import at.study.automation.cucumber.PageObjectHelper;
import at.study.automation.model.project.Project;
import at.study.automation.model.user.User;
import at.study.automation.ui.browser.BrowserUtils;
import at.study.automation.ui.pages.HeaderPage;
import at.study.automation.ui.pages.LoginPage;
import at.study.automation.ui.pages.ProjectsPage;
import at.study.automation.ui.pages.UserTablePage;
import at.study.automation.utils.CompareUtils;
import at.study.automation.utils.StringUtils;
import cucumber.api.java.ru.*;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static at.study.automation.allure.AllureAssert.*;
import static at.study.automation.ui.browser.BrowserUtils.*;
import static at.study.automation.ui.pages.LoginPage.getPage;
import static at.study.automation.utils.CompareUtils.assertListSortedByDateDesc;

public class UiSteps {

    @И("Авторизоваться как пользователь \"(.+)\"")
    public void authByUser(String userStashId) {
        User user = Context.getStash().get(userStashId, User.class);
        getPage(LoginPage.class).login(user);
    }

    @И("Авторизоваться по логину \"(.+)\" и паролю \"(.+)\"")
    public void authByLoginAndPassword(String login, String password) {
        getPage(LoginPage.class).login(login, password);
    }

    @И("На странице \"(.+)\" отображается элемент \"(.+)\"")
    public void assertPageElementIsDeployed(String pageName, String elementName) {

        assertTrue(
                isElementPresent(PageObjectHelper.findElement(pageName, elementName)),
                "Элемент отображается"
        );
    }

    @И("На странице \"(.+)\" не отображается элемент \"(.+)\"")
    public void assertPageElementIsNotDeployed(String pageName, String elementName) {

        assertFalse(
                isElementPresent(PageObjectHelper.findElement(pageName, elementName)),
                "Элемент не отображается"
        );
    }

    @И("На странице отображается проект \"(.+)\"")
    public void assertProjectIsDeployed(String elementName) {
        Project project = Context.getStash().get(elementName, Project.class);

        assertTrue(
                isElementPresent(
                        project.getName()),
                "Элемент отображается"
        );
    }

    @Также("В заголовке страницы текст элемента Вошли как \"(.+)\" верный логин текущего администратора")
    public void assertEnteredAs(String userStashId) {
        User admin = Context.getStash().get(userStashId, User.class);
        assertEquals(
                getPage(HeaderPage.class).userActive.getText(),
                "Вошли как " + admin.getLogin(),
                "Текст элемента \"Вошли как \"" + admin.getLogin()
        );
    }

    @Если("На странице \"(.+)\" нажать на элемент \"(.+)\"")
    public void clickOnElementOnPage(String pageName, String elementName) {
        PageObjectHelper.findElement(pageName, elementName).click();
    }

    @И("На странице \"(.+)\" в поле \"(.+)\" ввести текст \"(.+)\"")
    public void sendKeysToElementOnPage(String pageName, String elementName, String charSequence) {
        PageObjectHelper.findElement(pageName, elementName).sendKeys(charSequence);
    }

    @И("На странице \"(.+)\" тексты элементов \"(.+)\" отсортированы по дате по убыванию")
    public void assertElementsTextsIsSortedByDateDesc(String pageName, String elementsName) {
        List<WebElement> elements = PageObjectHelper.findElements(pageName, elementsName);
        List<String> elementsText = getElementsText(elements);
        assertListSortedByDateDesc(elementsText);
    }

    @Когда("Нажать на кнопку Войти")
    public void clickOnLoginButton() {
        click(getPage(HeaderPage.class).loginButton);
    }


    @И("На странице не отображается проект \"(.+)\"")
    public void assertProjectIsNotDeployed(String projectStashId) {
        Project project = Context.getStash().get(projectStashId, Project.class);

        assertFalse(
                isElementPresent(project.getName()),
                "Элемент не отображается"
        );
    }

    @И("Имя проекта совпадает с именем проекта \"(.+)\"")
    public void assertProjectStashIdText(String projectStashId) {
        Project project = Context.getStash().get(projectStashId, Project.class);

        assertEquals(
                getPage(ProjectsPage.class).getProject(project.getName()).getText(),
                project.getName(),
                "Имя проекта " + "\"" + project.getName() + "\""
        );
    }

    @И("Описание проекта совпадает с описанием проекта \"(.+)\"")
    public void assertProjectDescriptionText(String projectStashId) {
        Project project = Context.getStash().get(projectStashId, Project.class);

        assertEquals(
                getPage(ProjectsPage.class).getProjectDescription(project.getDescription()).getText(),
                project.getDescription(),
                "Описание проекта " + "\"" + project.getDescription() + "\""
        );
    }

    @Тогда("На странице отображается таблица пользователей")
    public void assertUsersTableIsDeployed() {
        UserTablePage userTablePage = getPage(UserTablePage.class);

        assertTrue(
                isElementPresent(userTablePage.usersTable),
                "Таблица с пользователями отображается"
        );
    }

    @Тогда("На странице в шапке таблицы нажать на \"(.+)\"")
    public void sortingUserTable(String columnHeadName) {
        UserTablePage userTablePage = getPage(UserTablePage.class);

        click(userTablePage.button(columnHeadName));
    }

    @Тогда("Таблица пользователей отсортирована по столбцу \"(.+)\" по алфавиту \"(.+)\"")
    public void sortingTable(String columnHeadName, String comparatorType) {
        List<WebElement> columnContent = PageObjectHelper.findElements("Пользователи", columnHeadName);

        List<String> columnContentText = BrowserUtils.getElementsText(columnContent);
        Comparator<String> comparator = CompareUtils.getComparator(comparatorType);

        List<String> columnContentTextCopy = new ArrayList<>(columnContentText);
        columnContentTextCopy.sort(comparator);

        assertEquals(columnContentText, columnContentTextCopy);
    }

    @Тогда("На странице \"(.+)\" заполнить поле \"(.+)\" случайными английскими символами")
    public void fillInTheField(String pageName, String elementName) {
        WebElement webElement = PageObjectHelper.findElement(pageName, elementName);

        sendKeys(webElement, StringUtils.randomEnglishString(10));
    }

    @Тогда("На странице \"(.+)\" заполнить поле \"(.+)\" случайным E-Mail адресом")
    public void fillInTheFieldEmail(String pageName, String elementName) {
        WebElement webElement = PageObjectHelper.findElement(pageName, elementName);

        sendKeys(webElement, StringUtils.randomEmail());
    }
}

