package at.study.automation.tests.ui;

import at.study.automation.model.project.Project;
import at.study.automation.model.user.User;
import at.study.automation.ui.browser.BrowserUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ViewingPrivateProjectByTheAdminTest extends BaseUiTest {

    private User admin;
    private Project project;

    @BeforeMethod
    public void prepareFixtures() {
        admin = new User() {{
            setIsAdmin(true);
        }}.create();

        project = new Project() {{
            setIsPublic(false);
        }}.create();

        openBrowser();
    }

    // TODO: Нужно дорабатывать
    @Test
    public void viewingPrivateProjectByTheAdminTest() {
        headerPage.loginButton.click();
        loginPage.login(admin);

        Assert.assertEquals(headerPage.homePage.getText(), "Домашняя страница");

        headerPage.projects.click();

        Assert.assertTrue(BrowserUtils.isElementPresent(projectsPage.getProject(project.getName())));
        Assert.assertEquals(projectsPage.getProjectDescription(project.getDescription()).getText(), project.getDescription());
    }
}
