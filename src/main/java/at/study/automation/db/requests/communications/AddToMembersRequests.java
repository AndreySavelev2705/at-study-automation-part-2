package at.study.automation.db.requests.communications;

import at.study.automation.db.connection.PostgresConnection;
import at.study.automation.model.Addable;
import at.study.automation.model.project.Project;
import at.study.automation.model.user.User;

import java.time.LocalDateTime;

public class AddToMembersRequests implements Addable {

    @Override
    public void add(Project project, User user) {
        String query = "INSERT INTO public.members\n" +
                "(id, user_id, project_id, created_on, mail_notification)\n" +
                "VALUES(DEFAULT, ?, ?, ?, ?) RETURNING id;\n";

        PostgresConnection.INSTANCE.executeQuery(
                query,
                user.getId(),
                project.getId(),
                LocalDateTime.now(),
                new Boolean(false)
        ).get(0).get("id");
    }

    public Integer getMemberId(Project project, User user) {
        String query = "SELECT id FROM members WHERE user_id = ? AND project_id = ?";

        return (Integer) PostgresConnection.INSTANCE.executeQuery(
                query,
                user.getId(),
                project.getId()
        ).get(0).get("id");
    }
}
