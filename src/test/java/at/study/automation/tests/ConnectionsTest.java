package at.study.automation.tests;

import at.study.automation.model.project.Project;
import at.study.automation.model.role.Role;
import at.study.automation.model.user.User;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class ConnectionsTest {
    Project project;
    List<User> users;
    List<Role> roles;

    @BeforeMethod
    public void prepareFixture() {
        project = new Project();
        users = Arrays.asList(new User(), new User());
        roles = Arrays.asList(new Role(), new Role(), new Role());
    }

    @Test
    public void workingWithUserAndHisProjectInDbTest() {
        for (Role role : roles) {
            role.addProject(project, users);
            role.create();
        }

        for (User user : users) {
            user.addProject(project, roles);
            user.create();
        }

        for (User user : users) {
            project.addUser(user, roles);
        }
        project.create();

    }
}
