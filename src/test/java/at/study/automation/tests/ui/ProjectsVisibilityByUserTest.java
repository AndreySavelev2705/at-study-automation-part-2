package at.study.automation.tests.ui;

import at.study.automation.model.project.Project;
import at.study.automation.model.role.Permissions;
import at.study.automation.model.role.Role;
import at.study.automation.model.user.User;
import at.study.automation.ui.browser.BrowserUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;

public class ProjectsVisibilityByUserTest extends BaseUiTest{
    private User user;

    private Project project1;
    private Project project2;
    private Project project3;

    @BeforeMethod
    public void prepareFixtures() {

        List<Permissions> permissions = Collections.singletonList(
                Permissions.VIEW_ISSUES
        );

        Role role = new Role() {{
            setPermissions(permissions);
        }}.create();

        project1 = new Project().create();

        project2 = new Project(){{
            setIsPublic(false);
        }}.create();

        project3 = new Project(){{
            setIsPublic(false);
        }};

        user = new User(){{
            addProject(project3, Collections.singletonList(role));
        }}.create();

        project3.addUser(user, Collections.singletonList(role));
        project3.create();

        openBrowser();
    }

    @Test
    public void projectsVisibilityByUserTest() {
        headerPage.loginButton.click();
        loginPage.login(user);

        assertEquals(homePage.homePageHeader.getText(), "Домашняя страница");

        headerPage.projects.click();

        assertEquals(projectsPage.getProject(project1.getName()).getText(), project1.getName());
        assertEquals(projectsPage.getProjectDescription(project1.getDescription()).getText(), project1.getDescription());

        assertFalse(BrowserUtils.isElementPresent(project2.getName()));

        assertEquals(projectsPage.getProject(project3.getName()).getText(), project3.getName());
        assertEquals(projectsPage.getProjectDescription(project3.getDescription()).getText(), project3.getDescription());
    }
}
