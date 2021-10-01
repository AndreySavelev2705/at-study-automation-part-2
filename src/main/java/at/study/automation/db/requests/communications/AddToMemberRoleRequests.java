package at.study.automation.db.requests.communications;

import at.study.automation.db.connection.PostgresConnection;
import at.study.automation.model.Addable;
import at.study.automation.model.project.Project;
import at.study.automation.model.role.Role;
import at.study.automation.model.user.User;

import java.util.List;

public class AddToMemberRoleRequests implements Addable {

    @Override
    public void add(Project project, User user) {
        String query = "INSERT INTO public.member_roles\n" +
                "(id, member_id, role_id, inherited_from)\n" +
                "VALUES(DEFAULT, ?, ?, ?);\n";

        List<Role> roles = project.getUsersAndRolesOfProject().get(user);

        for (int i = 0; i < roles.size(); i++) {

            PostgresConnection.INSTANCE.executeQuery(
                    query,
                    new AddToMembersRequests().getMemberId(project, user),
                    roles.get(i).getId(),
                    null
            );
        }
    }
}
