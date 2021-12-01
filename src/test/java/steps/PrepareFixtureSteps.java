package steps;

import at.study.automation.context.Context;
import at.study.automation.cucumber.validators.UserParametersValidator;
import at.study.automation.model.user.*;
import cucumber.api.java.ru.И;
import cucumber.api.java.ru.Пусть;
import io.cucumber.datatable.DataTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PrepareFixtureSteps {

    @Пусть("Имеется список E-Mail адресов \"(.+) \":")
    public void createEmails(String emailsStashId, DataTable dataTable) {
        // TODO: EmailValidator

        List<Map<String, String>> maps = dataTable.asMaps();
        List<Email> emails = new ArrayList<>();

        maps.forEach(map -> {
            String address = map.get("Адрес");
            Boolean isDefault = Boolean.parseBoolean(map.get("По умолчанию"));
            Boolean notify = Boolean.parseBoolean(map.get("Уведомления"));

            Email email = new Email()
                    .setAddress(address)
                    .setIsDefault(isDefault)
                    .setNotify(notify);

            emails.add(email);
        });

        Context.getStash().put(emailsStashId, emails);
    }

    @Пусть("В системе есть пользователь \"(.+)\" с параметрами:")
    public void createAdminUser(String userStashId, Map<String, String> parameters) {
        UserParametersValidator.validateUserParameters(parameters.keySet());

        User user = new User();

        if (parameters.containsKey("Администратор")) {
            Boolean isAdmin = Boolean.parseBoolean(parameters.get("Администратор"));
            user.setIsAdmin(isAdmin);
        }
        if (parameters.containsKey("Статус")) {
            String statusDescription = parameters.get("Статус");
            Status status = Status.of(statusDescription);
            user.setStatus(status);
        }
        if (parameters.containsKey("Уведомления о новых событиях")) {
            String mailNotificationDescription = parameters.get("Уведомления о новых событиях");
            MailNotification mn = MailNotification.of(mailNotificationDescription);
            user.setMailNotification(mn);
        }
        if (parameters.containsKey("E-Mail")) {
            String emailsStashId = parameters.get("E-Mail");
            List<Email> emails = Context.getStash().get(emailsStashId, List.class);
            user.setEmails(emails);
        }
        if (parameters.containsKey("Api-ключ")) {
            user.setTokens(Collections.singletonList(new Token(user)));
        }

        user.create();
        Context.getStash().put(userStashId, user);
    }

    @Пусть("В системе есть пользователь не администратор \"(.+)\" с параметрами:")
    public void createNotAdminUser(String userStashId, Map<String, String> parameters) {
        UserParametersValidator.validateUserParameters(parameters.keySet());

        User user = new User();

        if (parameters.containsKey("Администратор")) {
            Boolean isAdmin = Boolean.parseBoolean((parameters.get("Администратор")));
            user.setIsAdmin(isAdmin);
        }
        if (parameters.containsKey("Api-ключ")) {
            user.setTokens(Collections.singletonList(new Token(user)));
        }

        user.create();
        Context.getStash().put(userStashId, user);
    }

    @И("Создан еще один пользователь \"(.+)\" без прав администратора и без Api-ключа")
    public void createDefaultUser(String defaultUserStashId) {
        User user = new User().create();

        Context.getStash().put(defaultUserStashId, user);
    }
}