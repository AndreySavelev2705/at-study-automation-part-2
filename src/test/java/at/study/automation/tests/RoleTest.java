package at.study.automation.tests;

import at.study.automation.db.requests.RoleRequests;
import at.study.automation.model.project.Project;
import at.study.automation.model.role.Role;
import at.study.automation.model.user.User;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class RoleTest {

    @Test
    public void roleCreationTest() {
        Role role = new Role();
        new RoleRequests().create(role);

        Role role1 = new Role().create();
    }

    @Test
    public void roleUpdateTest() {
        Role role = new Role();
        new RoleRequests().create(role);

        role.setAssignable(true);

        new RoleRequests().update(role.getId(), role);
    }

    @Test
    public void roleDeleteTest() {
        Role role = new Role();
        new RoleRequests().create(role);

        new RoleRequests().delete(role.getId());
    }

    @Test
    public void addProjectAnUsers() {
        Role role = new Role();
        List<User> usersForRole;

        for (int i = 0; i < 5; i++) {
            usersForRole = new ArrayList<>();

            for (int j = 0; j < 3; j++) {
                usersForRole.add(new User());
            }
            Project project = new Project();
        }
    }
}
