package at.study.automation.tests.ui;

import at.study.automation.model.project.Project;
import at.study.automation.model.project.Status;
import at.study.automation.model.user.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ViewingPrivateProjectByTheAdminTest extends BaseUiTest {

    User admin;
    Project project;

    @BeforeMethod
    public void prepareFixtures() {
        admin = new User() {{
            setIsAdmin(true);
        }}.create();

        project = new Project() {{
            setStatus(Status.CLOSED);
        }}.create();

        //project = new Project().create();

        openBrowser("/login");
    }

    // TODO: Нужно дорабатывать
    @Test
    public void viewingPrivateProjectByTheAdminTest() {
        loginPage.login(admin);

        headerPage.projects.click();
        // //div[@id='projects-index']//a[text()='Savelev_project_for_testingjpvlf']
        String projectName = project.getName();
        //ProjectsPage
        //Thread.sleep(5000);
    }
}
