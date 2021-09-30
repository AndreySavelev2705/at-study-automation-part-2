package at.study.automation.tests;

import at.study.automation.model.project.Project;
import at.study.automation.model.role.Role;
import at.study.automation.model.user.User;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserTest {

    @Test
    public void userCreationTest() {
        User user = new User();

        User user2 = new User();
        user2.setPassword("qwriqwiqw");
        user2.setFirstName("Иван");
        user2.setLastName("Петров");
//        user2.setTokens(Collections.singletonList(new Token()));
//        user2.setEmails(Collections.singletonList(new Email()));
    }

    @Test
    public void addProjectToUser() {
        User user = new User();
        Map<Project, List<Role>> usersAndRolesOfProject = new HashMap<>();
        List<Role> rolesForProject;

        for (int i = 0; i < 5; i++) {
            rolesForProject = new ArrayList<>();

            for (int j = 0; j < 3; j++) {
                rolesForProject.add(new Role());
            }
            Project project = new Project();
            usersAndRolesOfProject.put(project, rolesForProject);
            user.addProject(project, rolesForProject);
        }

        Map<Project, List<Role>> usersAndRolesOfProjectResult = user.getProjectsAndRolesOfUser();
    }
}
