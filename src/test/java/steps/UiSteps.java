package steps;

import at.study.automation.allure.AllureAssert;
import at.study.automation.context.Context;
import at.study.automation.cucumber.PageObjectHelper;
import at.study.automation.db.requests.UserRequests;
import at.study.automation.model.project.Project;
import at.study.automation.model.user.User;
import at.study.automation.ui.pages.*;
import at.study.automation.utils.StringUtils;
import cucumber.api.java.ru.*;
import org.openqa.selenium.WebElement;

import java.util.List;

import static at.study.automation.allure.AllureAssert.*;
import static at.study.automation.ui.browser.BrowserUtils.*;
import static at.study.automation.ui.pages.LoginPage.getPage;
import static at.study.automation.utils.CompareUtils.*;

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

        if (pageName.equals("Домашняя страница") && elementName.equals("Домашняя страница")) {
            assertEquals(
                    getPage(HomePage.class).homePageHeader.getText(),
                    elementName
            );
            return;
        }
        if (pageName.equals("Проекты") && elementName.equals("Проекты")) {
            assertEquals(
                    getPage(ProjectsPage.class).projectsLabel.getText(),
                    elementName
            );
            return;
        }
        if (pageName.equals("Администрирование") && elementName.equals("Администрирование")) {
            assertEquals(
                    getPage(AdministrationPage.class).administrationHeader.getText(),
                    elementName
            );
            return;
        }
        if (pageName.equals("Новый пользователь") && elementName.equals("Пользователи » Новый пользователь")) {
            assertEquals(
                    getPage(CreateNewUserPage.class).breadCrumbs.getText(),
                    elementName
            );
            return;
        }
        if (pageName.equals("Заголовок страницы") && elementName.equals("Моя страница")) {
            assertEquals(
                getPage(HeaderPage.class).myPage.getText(),
                elementName,
                "Текст элемента \"" + elementName + "\""
            );
            return;
        }
        if (pageName.equals("Заголовок страницы") && elementName.equals("Проекты")) {
            assertEquals(
                getPage(HeaderPage.class).projects.getText(),
                    elementName,
                "Текст элемента \"" + elementName + "\""
            );
            return;
        }
        if (pageName.equals("Заголовок страницы") && elementName.equals("Помощь")) {
            assertEquals(
                    getPage(HeaderPage.class).help.getText(),
                    elementName,
                    "Текст элемента \"" + elementName + "\""
            );
            return;
        }
        if (pageName.equals("Заголовок страницы") && elementName.equals("Моя учётная запись")) {
            AllureAssert.assertEquals(
                getPage(HeaderPage.class).myAccount.getText(),
                    elementName
            );
            return;
        }
        if (pageName.equals("Заголовок страницы") && elementName.equals("Выйти")) {
            assertEquals(
                    getPage(HeaderPage.class).logout.getText(),
                    elementName,
                    "Текст элемента \"" + elementName + "\""
            );
            return;
        }
        if (pageName.equals("Заголовок страницы") && elementName.equals("Поиск")) {
            assertTrue(
                isElementPresent(getPage(HeaderPage.class).search),
                "Элемент отображается"
            );
            return;
        }
        if (pageName.equals("Заголовок страницы") && elementName.equals("Регистрация")) {
            assertTrue(
                    isElementPresent(getPage(HeaderPage.class).search),
                    "Элемент отображается"
            );
            return;
        }
        else throw new RuntimeException();
    }

    @И("На странице \"(.+)\" не отображается элемент \"(.+)\"")
    public void assertPageElementIsNotDeployed(String pageName, String elementName) {

        if (pageName.equals("Заголовок страницы") && elementName.equals("Администрирование")) {
            assertFalse(
                isElementPresent(getPage(HeaderPage.class).administration),
                "Элемент не отображается"
        );
            return;
        }
        if (pageName.equals("Заголовок страницы") && elementName.equals("Войти")) {
            assertFalse(
                    isElementPresent(getPage(HeaderPage.class).loginButton),
                    "Элемент не отображается"
            );
            return;
        }
        if (pageName.equals("Заголовок страницы") && elementName.equals("Регистрация")) {
            assertFalse(
                    isElementPresent(getPage(HeaderPage.class).register),
                    "Элемент не отображается"
            );
            return;
        }
        else throw new RuntimeException();
    }

    @И("На странице \"Проекты\" отображается проект \"(.+)\"")
    public void assertProjectIsDeployed(String elementName) {
        Project project = Context.getStash().get(elementName, Project.class);

        assertTrue(
                isElementPresent(
                        project.getName()),
                "Элемент отображается"
        );
    }

    @Также("В заголовке страницы текст элемента Администрирование - \"(.+)\"")
    public void assertAdministrationText(String expectedText) {
        assertEquals(
                getPage(HeaderPage.class).administration.getText(),
                expectedText,
                "Текст элемента \"" + expectedText + "\""
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

    @Когда("Нажать на кнопку \"(.+)\"")
    public void clickOnLoginButton(String buttonName) {

        click(getPage(HeaderPage.class).loginButton, buttonName);
    }

    @Когда("На главной странице нажать администрирование \"(.+)\"")
    public void clickOnAdministrationButton(String buttonName) {
        click(getPage(HeaderPage.class).administration, buttonName);
    }

    @Тогда("Отображается сообщение \"(.+)\"")
    public void assertMessageDisplay(String message) {
        assertEquals(
                getPage(LoginPage.class).errorFlash.getText(),
                message,
                "Текст элемента \"" + message + "\""
        );
    }

    @И("В заголовке страницы текст элемента Войти - \"(.+)\"")
    public void assertLoginButtonText(String expectedText) {
        assertEquals(
                getPage(HeaderPage.class).loginButton.getText(),
                expectedText,
                "Текст элемента \"" + expectedText + "\""
        );
    }

    @И("На странице не отображается проект \"(.+)\"")
    public void assertProjectIsNotDeployed(String projectName) {
        Project project = Context.getStash().get(projectName, Project.class);

        AllureAssert.assertFalse(
                isElementPresent(
                        project.getName()),
                "Элемент не отображается"
        );
    }

    @И("Имя проекта совпадает с именем проекта \"(.+)\"")
    public void assertProjectNameText(String projectName) {
        Project project = Context.getStash().get(projectName, Project.class);

        assertEquals(
                getPage(ProjectsPage.class).getProject(project.getName()).getText(),
                project.getName(),
                "Имя проекта " + "\"" + project.getName() + "\""
        );
    }

    @И("Описание проекта совпадает с описанием проекта \"(.+)\"")
    public void assertProjectDescriptionText(String projectName) {
        Project project = Context.getStash().get(projectName, Project.class);

        assertEquals(
                getPage(ProjectsPage.class).getProjectDescription(project.getDescription()).getText(),
                project.getDescription(),
                "Описание проекта " + "\"" + project.getDescription() + "\""
        );
    }

    @Тогда("На странице \"(.+)\" отображается таблица \"(.+)\"")
    public void assertUsersTableIsDeployed(String pageNameStashId, String tableName) {
        UserTablePage userTablePage = getPage(UserTablePage.class);

        assertTrue(
                isElementPresent(userTablePage.usersTable),
                "Таблица с пользователями отображается"
        );

        Context.getStash().put(pageNameStashId, userTablePage);
    }

    @Тогда("В шапке таблицы \"(.+)\" нажать на \"(.+)\"")
    public void sortingUserTable(String tableName, String columnName) {

        if (tableName.equals("Пользователи") && columnName.equals("Пользователь")) {
            UserTablePage userTablePage = Context.getStash().get(tableName, UserTablePage.class);

            click(userTablePage.button(columnName), columnName + ": сортировка убыванию");
            List<String> usersBy = getElementsText(userTablePage.usersLogins);

            Context.getStash().put(columnName, usersBy);
            return;
        }
        if (tableName.equals("Пользователи") && columnName.equals("Фамилия")) {
            UserTablePage userTablePage = Context.getStash().get(tableName, UserTablePage.class);

            click(userTablePage.button(columnName), columnName + ": сортировка убыванию");
            List<String> usersBy = getElementsText(userTablePage.usersLastNames);

            Context.getStash().put(columnName, usersBy);
            return;
        }
        if (tableName.equals("Пользователи") && columnName.equals("Имя")) {
            UserTablePage userTablePage = Context.getStash().get(tableName, UserTablePage.class);

            click(userTablePage.button(columnName), columnName + ": сортировка убыванию");
            List<String> usersBy = getElementsText(userTablePage.usersFirstNames);

            Context.getStash().put(columnName, usersBy);
            return;
        }
    }

    @Тогда("Таблица \"(.+)\" отсортирована по полю \"(.+)\" по возрастанию")
    public void sortingTableAsc(String tableName, String columnName) {
        if (tableName.equals("Пользователи") && columnName.equals("Пользователь")) {
            List usersBy = Context.getStash().get(columnName, List.class);
            assertListSortedByUserNameAsc(usersBy);
            return;
        }
        if (tableName.equals("Пользователи") && columnName.equals("Фамилия")) {
            List usersBy = Context.getStash().get(columnName, List.class);
            assertListSortedByUserLastNameAsc(usersBy);
            return;
        }

        if (tableName.equals("Пользователи") && columnName.equals("Имя")) {
            List usersBy = Context.getStash().get(columnName, List.class);
            assertListSortedByUserFirstNameAsc(usersBy);
            return;
        }
        throw new RuntimeException();
    }

    @Тогда("Таблица \"(.+)\" отсортирована по полю \"(.+)\" по убыванию")
    public void sortingTableDesc(String tableName, String columnName) {
        if (tableName.equals("Пользователи") && columnName.equals("Пользователь")) {
            List usersBy = Context.getStash().get(columnName, List.class);
            assertListSortedByUserNameDesc(usersBy);
            return;
        }
        if (tableName.equals("Пользователи") && columnName.equals("Фамилия")) {
            List usersBy = Context.getStash().get(columnName, List.class);
            assertListSortedByUserLastNameDesc(usersBy);
            return;
        }
        if (tableName.equals("Пользователи") && columnName.equals("Имя")) {
            List usersBy = Context.getStash().get(columnName, List.class);
            assertListSortedByUserLastNameDesc(usersBy);
            return;
        }
        throw new RuntimeException();
    }

    @Тогда("На странице \"(.+)\" заполнить поле \"(.+)\"")
    public void fillInTheField(String pageName, String elementName) {
        WebElement webElement = PageObjectHelper.findElement(pageName, elementName);

        if (elementName.equals("Email")) {
            sendKeys(webElement, StringUtils.randomEmail());
        }
        sendKeys(webElement, StringUtils.randomEnglishString(10));
    }

    @Затем("На той же странице \"(.+)\" нажать на элемент \"(.+)\" для создания нового пользователя \"(.+)\"")
    public void clickOnElementOnPageNewUser(String pageName, String elementName, String userStashId) {
        PageObjectHelper.findElement(pageName, elementName).click();

        String login = getPage(CreateNewUserPage.class).flashNotice.getText().substring(13, 23);

        User user = new UserRequests().read(login);

        Context.getStash().put(userStashId, user);
    }

    @И("На странице \"Новый пользователь\" отображается сообщение что создан пользователь \"(.+)\"")
    public void assertNewUserCreatedText(String userStashId) {

        CreateNewUserPage createNewUserPage = getPage(CreateNewUserPage.class);

        User userForCreating = Context.getStash().get(userStashId, User.class);

        assertEquals(
                createNewUserPage.flashNotice.getText(),
                "Пользователь " + userForCreating.getLogin() + " создан.",
                "Сообщение \"Пользователь создан\""
        );
    }
}

