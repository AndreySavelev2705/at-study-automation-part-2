package at.study.automation.ui.pages;

import at.study.automation.model.user.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class LoginPage extends Page {

    @FindBy(xpath = "//input[@id='username']")
    private WebElement loginInput;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@id='login-submit']")
    private WebElement signInButton;

    @FindBy(xpath = "//div[@id='flash_error']")
    public WebElement errorFlash;

    /**
     * Метод выполняет авторизацию по переданному в его параметры логину и паролю
     *
     * @param login - логин, который будет использоваться при авторизации
     * @param password - пароль, который будет использоваться при авторизации
     */
    public void login(String login, String password) {
        loginInput.sendKeys(login);
        passwordInput.sendKeys(password);
        signInButton.click();
    }

    /**
     * Метод выполняет авторизацию по переданному в его параметры логину и паролю
     * конкретного пользователя
     *
     * @param user - пользователь на основе, логина и пароля, которого выролняется авторизация
     */
    public void login(User user) {
        login(user.getLogin(), user.getPassword());
    }
}
