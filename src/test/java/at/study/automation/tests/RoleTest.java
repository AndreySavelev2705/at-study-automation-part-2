package at.study.automation.tests;

import at.study.automation.db.requests.RoleRequests;
import at.study.automation.model.role.Role;
import org.testng.annotations.Test;

public class RoleTest {

    @Test
    public void roleCreationTest() {
        Role role = new Role();
        new RoleRequests().create(role);
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
}
