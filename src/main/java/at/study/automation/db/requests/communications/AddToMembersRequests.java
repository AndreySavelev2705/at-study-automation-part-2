package at.study.automation.db.requests.communications;

import at.study.automation.db.connection.PostgresConnection;
import io.qameta.allure.Step;

import java.time.LocalDateTime;

public class AddToMembersRequests {

    @Step("Связывание пользователя с Id {0} с проектом с Id {1} - создание участника проекта")
    public Integer addMember(Integer userId, Integer projectId) {
        String query = "INSERT INTO public.members\n" +
                "(id, user_id, project_id, created_on, mail_notification)\n" +
                "VALUES(DEFAULT, ?, ?, ?, ?) RETURNING id;\n";

        return (Integer) PostgresConnection.INSTANCE.executeQuery(
                query,
                userId,
                projectId,
                LocalDateTime.now(),
                false
        ).get(0).get("id");
    }

    @Step("Связывание участника проекта с Id {0} и его ролью с Id {1} на этом проекте")
    public void addMemberRoles(Integer memberId, Integer roleId) {
        String query = "INSERT INTO public.member_roles\n" +
                "(id, member_id, role_id, inherited_from)\n" +
                "VALUES(DEFAULT, ?, ?, ?);\n";

        PostgresConnection.INSTANCE.executeQuery(
                query,
                memberId,
                roleId,
                null
        );
    }
}
