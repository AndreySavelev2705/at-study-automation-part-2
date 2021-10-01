package at.study.automation.tests;

import at.study.automation.db.requests.ProjectRequests;
import at.study.automation.db.requests.RoleRequests;
import at.study.automation.db.requests.UserRequests;
import at.study.automation.model.communications.AddToMemberRoleRequests;
import at.study.automation.model.communications.AddToMembersRequests;
import at.study.automation.model.project.Project;
import at.study.automation.model.role.Role;
import at.study.automation.model.user.User;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ConnectionsTest {

    @Test
    public void workingWithUserAndHisProjectInDbTest() {
        Project project = new Project();
        User user = new User();

        List<Role> roles = new ArrayList<>();
        roles.add(new Role());
        roles.add(new Role());
        roles.add(new Role());

        project.addUser(user, roles);
        user.addProject(project, project.getUsersAndRolesOfProject().get(user));

        Set<User> set =  project.getUsersAndRolesOfProject().keySet();

        List<User> users = new ArrayList<>(set);

        // Добавление ролей в бд
        for (Role role : roles) {

            role.addUser(project, users);

            new RoleRequests().create(role);
        }

        new ProjectRequests().create(project);
        new UserRequests().create(user);

        new AddToMembersRequests().add(project, user);
        new AddToMemberRoleRequests().add(project, user);

        int memberId = new AddToMembersRequests().getMemberId(project, user);

        new ProjectRequests().delete(project.getId());
        new UserRequests().delete(user.getId());

        for (Role role : roles) {

            role.addUser(project, users);

            new RoleRequests().delete(role.getId());
        }
    }
}
