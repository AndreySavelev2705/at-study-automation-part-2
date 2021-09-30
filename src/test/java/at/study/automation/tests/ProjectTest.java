package at.study.automation.tests;

import at.study.automation.db.requests.ProjectRequests;
import at.study.automation.model.project.Project;
import at.study.automation.model.role.Role;
import at.study.automation.model.user.User;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Project project = new Project();
        Map<User, List<Role>> usersAndRolesOfProject = new HashMap<>();
        List<Role> rolesForProject;

        for (int i = 0; i < 5; i++) {
            rolesForProject = new ArrayList<>();

            for (int j = 0; j < 3; j++) {
                rolesForProject.add(new Role());
            }
            User user = new User();
            usersAndRolesOfProject.put(user, rolesForProject);
            project.addUser(user, rolesForProject);
        }
        Map<User, List<Role>> usersAndRolesOfProjectResult = project.getUsersAndRolesOfProject();
    }
}
