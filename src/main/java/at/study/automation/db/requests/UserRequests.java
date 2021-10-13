package at.study.automation.db.requests;

import at.study.automation.db.connection.PostgresConnection;
import at.study.automation.model.user.Language;
import at.study.automation.model.user.MailNotification;
import at.study.automation.model.user.Status;
import at.study.automation.model.user.User;

import java.util.List;
import java.util.Map;

public class UserRequests extends BaseRequests implements Create<User>, Update<User>, Delete<User>, Read<User> {

    @Override
    public void create(User user) {
        String query = "INSERT INTO public.users\n" +
                "(id, login, hashed_password, firstname, lastname, \"admin\", " +
                "status, last_login_on, \"language\", auth_source_id," +
                " created_on, updated_on, \"type\", identity_url, mail_notification, " +
                "salt, must_change_passwd, passwd_changed_on)\n" +
                "VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;\n";

        Integer userId = (Integer) PostgresConnection.INSTANCE.executeQuery(
                query,
                user.getLogin(),
                user.getHashedPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getIsAdmin(),
                user.getStatus().statusCode,
                user.getLastLoginOn(),
                user.getLanguage().languageCode,
                user.getAuthSourceId(),
                user.getCreatedOn(),
                user.getUpdatedOn(),
                user.getType(),
                user.getIdentityUrl(),
                user.getMailNotification().name().toLowerCase(),
                user.getSail(),
                user.getMustChangePassword(),
                user.getPasswordChangedOn()
        ).get(0).get("id");
        user.setId(userId);
    }

    @Override
    public void delete(Integer id) {
        String query = "DELETE FROM public.users\n" +
                "WHERE id=?;\n";
        PostgresConnection.INSTANCE.executeQuery(query, id);
    }

    @Override
    public void update(Integer id, User user) {
        String query = "UPDATE public.users\n" +
                "SET login=?, hashed_password=?, firstname=?, lastname=?, \"admin\"=?, " +
                "status=?, last_login_on=?, \"language\"=?, auth_source_id=?, created_on=?, " +
                "updated_on=?, \"type\"=?, identity_url=?, mail_notification=?, salt=?, " +
                "must_change_passwd=?, passwd_changed_on=? \n" +
                "WHERE id=?;\n";

        PostgresConnection.INSTANCE.executeQuery(
                query,
                user.getLogin(),
                user.getHashedPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getIsAdmin(),
                user.getStatus().statusCode,
                user.getLastLoginOn(),
                user.getLanguage().languageCode,
                user.getAuthSourceId(),
                user.getCreatedOn(),
                user.getUpdatedOn(),
                user.getType(),
                user.getIdentityUrl(),
                user.getMailNotification().name().toLowerCase(),
                user.getSail(),
                user.getMustChangePassword(),
                user.getPasswordChangedOn(),
                id
        );
    }

    @Override
    public User read(Integer id) {
        String query = "SELECT * FROM users WHERE id = ?";
        List<Map<String, Object>> queryResult = PostgresConnection.INSTANCE.executeQuery(query, id);

        if (queryResult.size() != 0) {
            return from(queryResult.get(0));
        }
        return null;
    }

    // Делаем из мапы, с результатом запроса из бд, объект класса User и возвращаем его
    private User from(Map<String, Object> data) {
        return (User) new User()
                .setLogin((String)data.get("login"))
                .setHashedPassword((String)data.get("hashed_password"))
                .setFirstName((String) data.get("firstname"))
                .setLastName((String) data.get("lastname"))
                .setIsAdmin((Boolean) data.get("admin"))
                .setStatus(Status.getStatusByStatusCode((Integer) data.get("status")))
                .setLastLoginOn(null)
                .setLanguage(Language.getLanguageByLanguageCode((String) data.get("language")))
                .setAuthSourceId(null)
                .setType((String)data.get("type"))
                .setIdentityUrl(null)
                .setMailNotification(MailNotification.getEmailNotificationByDescription(data.get("mail_notification").toString().toUpperCase()))
                .setSail((String)data.get("salt"))
                .setCreatedOn(toLocalDate(data.get("created_on"))) // тут возвращается объект типа Timestamp
                .setUpdatedOn(toLocalDate(data.get("updated_on"))) // тут возвращается объект типа Timestamp
                .setId((Integer) data.get("id"));
    }
}
