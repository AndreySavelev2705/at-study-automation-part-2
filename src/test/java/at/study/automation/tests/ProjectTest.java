package at.study.automation.tests;

import at.study.automation.db.requests.ProjectRequests;
import at.study.automation.model.project.Project;
import org.testng.annotations.Test;

public class ProjectTest {

    @Test
    public void projectCreationTest() {
        Project project = new Project();
        new ProjectRequests().create(project);

        Project project1 = new Project().create();
    }

    @Test
    public void projectUpdateTest() {
        Project project = new Project();
        new ProjectRequests().create(project);

        project.setHomepage("Тест для проверки апдейта");

        new ProjectRequests().update(project.getId(), project);
    }

    @Test
    public void projectDeleteTest() {
        Project project = new Project();
        new ProjectRequests().create(project);

        new ProjectRequests().delete(project.getId());
    }

    @Test
    public void addUserTest() {
        Project project = new Project().create();
    }
}
