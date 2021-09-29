package at.study.automation.tests;

import at.study.automation.db.connection.PostgresConnection;
import at.study.automation.db.requests.EmailRequests;
import at.study.automation.model.user.Email;
import at.study.automation.model.user.User;
import lombok.SneakyThrows;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class SimpleDbConnectionTest {

    @Test
    @SneakyThrows
    public void  testDbConnection() {
        Class.forName("org.postgresql.Driver");

        String url = "jdbc:postgresql://edu-at.dfu.i-teco.ru:5432/db";
        Properties connectionProperties = new Properties();
        connectionProperties.setProperty("user", "redmine_user");
        connectionProperties.setProperty("password", "redmine_pass");
        Connection connection = DriverManager.getConnection(url, connectionProperties);

        String token = "56b6e04663a408face582d59a01434f3078e361a";
        String action = "feeds";
        String query = "SELECT * FROM tokens WHERE value = ? AND action = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, token);
        statement.setString(2, action);
        // Содержит курсор для передвижения по строкам вниз результирующей таблицы через метод next();
        ResultSet rs = statement.executeQuery();

        List<Map<String, Object>> result = new ArrayList<>();

        while (rs.next()) {
            Map<String, Object> oneLineResult = new HashMap<>();
            int columnCount = rs.getMetaData().getColumnCount();

            for (int i = 1; i <= columnCount; i++) {
                String key = rs.getMetaData().getColumnName(i);
                Object value = rs.getObject(i);
                oneLineResult.put(key, value);
            }
            result.add(oneLineResult);
        }

        System.out.println();
    }

    @Test
    public void postgresClientTest() {
        String query = "SELECT * FROM tokens WHERE value = ? AND action = ?";
        List<Map<String, Object>> result = PostgresConnection.INSTANCE.executeQuery(query, "56b6e04663a408face582d59a01434f3078e361a", "feeds");
    }

    @Test
    public void emailCreationTest() {
        User user = new User();
        Email email = new Email(user);
        email.setUserId(26539);

        new EmailRequests().create(email);
    }

    @Test
    public void readEmailFromDatabaseTest() {
        User user = new User();
        user.setId(26539);
        Email email = new EmailRequests(user).read(23323);
    }

    @Test
    public void readUserEmails() {
        User user = new User();
        user.setId(26539);

        List<Email> emails = new EmailRequests(user).readAll();
    }

    @Test
    public void deleteEmailTest() {
        new EmailRequests().delete(23323);
    }

    @Test
    public void emAILlUFECYCLEtEST() {
        User user = new User();
        user.setId(26539);

        Email email = new Email(user);

        new EmailRequests().create(email);

        email.setAddress("new_email@mail.ru");
        email.setIsDefault(false);

        new EmailRequests().update(email.getId(), email);

        new EmailRequests().delete(email.getId());
    }
}
