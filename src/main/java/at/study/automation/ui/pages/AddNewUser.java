package at.study.automation.ui.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddNewUser extends Page {
    @FindBy(xpath = "//div[@class='splitcontent']//input[@id='user_login']")
    public WebElement userLogin;
    @FindBy(xpath = "//div[@class='splitcontent']//input[@id='user_firstname']")
    public WebElement userFirstName;
    @FindBy(xpath = "//div[@class='splitcontent']//input[@id='user_lastname']")
    public WebElement userLastName;
    @FindBy(xpath = "//div[@class='splitcontent']//input[@id='user_mail']")
    public WebElement userMail;
    @FindBy(xpath = "//div[@id='password_fields']//input[@id='user_generate_password']")
    public WebElement generatePassword;
    @FindBy(xpath = "//div[@id='content']/form[@class='new_user']/p/input[@value='Создать']")
    public WebElement create;
    @FindBy(xpath = "//div[@class='flash notice']")
    public WebElement flashNotice;
    @FindBy(xpath = "//div[@id='content']/h2")
    public WebElement breadCrumbs;
}
