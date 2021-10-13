package at.study.automation.tests;

import at.study.automation.model.project.Project;
import at.study.automation.model.role.Permissions;
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

        List<Permissions> permissions = Arrays.asList(
                Permissions.EDIT_ISSUE_NOTES,
                Permissions.DELETE_MESSAGES,
                Permissions.ADD_ISSUE_NOTES,
                Permissions.ADD_DOCUMENTS,
                Permissions.ADD_MESSAGES,
                Permissions.LOG_TIME
        );

        roles.forEach(role -> role.setPermissions(permissions).create());
    }

    @Test
    public void workingWithUserAndHisProjectInDbTest() {
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
