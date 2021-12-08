package steps;

import at.study.automation.allure.AllureAssert;
import at.study.automation.context.Context;
import at.study.automation.cucumber.PageObjectHelper;
import at.study.automation.model.project.Project;
import at.study.automation.model.user.User;
import at.study.automation.ui.pages.HeaderPage;
import at.study.automation.ui.pages.HomePage;
import at.study.automation.ui.pages.LoginPage;
import at.study.automation.ui.pages.ProjectsPage;
import cucumber.api.java.ru.*;
import org.openqa.selenium.WebElement;

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

    @И("Тогда текст элемента Моя учетная запись - \"(.+)\"")
    public void assertMyAccountText(String expectedText) {
        AllureAssert.assertEquals(
                getPage(HeaderPage.class).myAccount.getText(),
                expectedText
        );
    }

    @И("Текст элемента Домашняя страница - \"(.+)\"")
    public void assertHomePageText(String expectedText) {
        AllureAssert.assertEquals(
                getPage(HomePage.class).homePageHeader.getText(),
                expectedText
        );
    }

    @И("Текст элемента Проекты - \"(.+)\"")
    public void assertProjectsPageText(String expectedText) {
        AllureAssert.assertEquals(
                getPage(ProjectsPage.class).projectsLabel.getText(),
                expectedText
        );
    }

    @Также("В заголовке страницы текст элемента Домашняя страница - \"(.+)\"")
    public void assertHomePageInHeaderPageText(String expectedText) {
        assertEquals(
                getPage(HeaderPage.class).homePage.getText(),
                expectedText,
                "Текст элемента \"" + expectedText + "\""
        );
    }

    @Также("В заголовке страницы текст элемента Моя страница - \"(.+)\"")
    public void assertMyPageText(String expectedText) {
        assertEquals(
                getPage(HeaderPage.class).myPage.getText(),
                expectedText,
                "Текст элемента \"" + expectedText + "\""
        );
    }

    @Также("В заголовке страницы текст элемента Проекты - \"(.+)\"")
    public void assertProjectsText(String expectedText) {
        assertEquals(
                getPage(HeaderPage.class).projects.getText(),
                expectedText,
                "Текст элемента \"" + expectedText + "\""
        );
    }

    @Также("В заголовке страницы текст элемента Помощь - \"(.+)\"")
    public void assertHelpText(String expectedText) {
        assertEquals(
                getPage(HeaderPage.class).help.getText(),
                expectedText,
                "Текст элемента \"" + expectedText + "\""
        );
    }

    @Также("В заголовке страницы текст элемента Выйти - \"(.+)\"")
    public void assertLogoutText(String expectedText) {
        assertEquals(
                getPage(HeaderPage.class).logout.getText(),
                expectedText,
                "Текст элемента \"" + expectedText + "\""
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

    @Также("В заголовке страницы текст элемента Администрирование не отображается")
    public void assertAdministrationFalse() {
        assertFalse(
                isElementPresent(getPage(HeaderPage.class).administration),
                "Элемент не отображается"
        );
    }

    @Также("В заголовке страницы текст элемента Войти не отображается")
    public void assertLoginButtonFalse() {
        assertFalse(
                isElementPresent(getPage(HeaderPage.class).loginButton),
                "Элемент не отображается"
        );
    }

    @Также("В заголовке страницы текст элемента Регистрация не отображается")
    public void assertRegisterButtonFalse() {
        assertFalse(
                isElementPresent(getPage(HeaderPage.class).register),
                "Элемент не отображается"
        );
    }

    @Также("На странице элемент Поиск отображается")
    public void assertSearchFalse() {
        assertTrue(
                isElementPresent(getPage(HeaderPage.class).search),
                "Элемент отображается"
        );
    }

    @Если("На странице (.+) нажать на элемент (.+)")
    public void clickOnElementOnPage(String pageName, String elementName) {
        PageObjectHelper.findElement(pageName, elementName).click();
    }

    @И("На странице (.+) в поле (.+) ввести текст (.+)")
    public void sendKeysToElementOnPage(String pageName, String elementName, String charSequence) {
        PageObjectHelper.findElement(pageName, elementName).sendKeys(charSequence);
    }

    @И("На странице (.+) тексты элементов (.+) отсортированы по дате по убыванию")
    public void assertElementsTextsIsSortedByDateDesc(String pageName, String elementsName) {
        List<WebElement> elements = PageObjectHelper.findElements(pageName, elementsName);
        List<String> elementsText = getElementsText(elements);
        assertListSortedByDateDesc(elementsText);
    }

    @Когда("Нажать на кнопку \"(.+)\"")
    public void clickOnLoginButton(String buttonName) {
        click(getPage(HeaderPage.class).loginButton, buttonName);
    }

    @Когда("На главной странице нажать \"(.+)\"")
    public void clickOnProjectsButton(String buttonName) {
        click(getPage(HeaderPage.class).projects, buttonName);
    }

    @Тогда("Отображается сообщение \"(.+)\"")
    public void assertMessageDisplay(String message) {
        assertEquals(
                getPage(LoginPage.class).errorFlash.getText(),
                message,
                "Текст элемента \""+ message + "\""
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

    @И("В заголовке страницы текст элемента Регистрация - \"(.+)\"")
    public void assertRegisterText(String expectedText) {
        assertEquals(
                getPage(HeaderPage.class).register.getText(),
                expectedText,
                "Текст элемента \"" + expectedText + "\""
        );
    }

    @И("На странице отображается проект \"(.+)\"")
    public void assertProjectIsDeployed(String projectName) {
        Project project = Context.getStash().get(projectName, Project.class);

        assertTrue(
                isElementPresent(
                        getPage(ProjectsPage.class).getProject(project.getName())),
                "Элемент отображается"
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
}
