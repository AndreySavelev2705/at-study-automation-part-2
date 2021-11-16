package steps;

import at.study.automation.allure.AllureAssert;
import at.study.automation.context.Context;
import at.study.automation.model.user.User;
import at.study.automation.ui.pages.HeaderPage;
import at.study.automation.ui.pages.LoginPage;
import cucumber.api.java.ru.И;

import static at.study.automation.ui.pages.LoginPage.*;

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
}
