package steps;

import at.study.automation.context.Context;
import at.study.automation.cucumber.validators.RoleParametersValidator;
import at.study.automation.cucumber.validators.UserParametersValidator;
import at.study.automation.model.project.Project;
import at.study.automation.model.role.Permissions;
import at.study.automation.model.role.Role;
import at.study.automation.model.user.*;
import cucumber.api.java.ru.Пусть;
import cucumber.api.java.ru.Также;
import io.cucumber.datatable.DataTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class PrepareFixtureSteps {

    @Пусть("Имеется список E-Mail адресов \"(.+) \":")
    public void createEmails(String emailsStashId, DataTable dataTable) {

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
    public void createUser(String userStashId, Map<String, String> parameters) {
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

    @Пусть("В системе существует набор ролей - \"(.+)\" с разрешениями:")
    public void createRole(String roleStashId, Map<String, String> parameters) {
        RoleParametersValidator.validateUserParameters(parameters.keySet());

        Role role = new Role();
        List<Permissions> permissions = null;

        if (parameters.containsKey("Просмотр задач")) {
            permissions = Collections.singletonList(
                    Permissions.VIEW_ISSUES
            );
        }

        role.setPermissions(permissions);
        role.create();
        Context.getStash().put(roleStashId, role);
    }

    @Также("Существует приватный проект \"(.+)\"")
    public void createPrivateProject(String projectNameStashId) {

        Project project = new Project() {{
            setIsPublic(false);
        }}.create();

        Context.getStash().put(projectNameStashId, project);
    }

    @Также("Существует проект \"(.+)\"")
    public void createProject(String projectName) {
        Project project = new Project().create();

        Context.getStash().put(projectName, project);
    }

    @Также("У пользователя \"(.+)\" с набором ролей \"(.+)\" есть доступ только к проекту \"(.+)\"")
    public void linkingProjectAndUserAndRoles(String userStashId, String roleStashId, String projectNameStashId) {
        Role role = Context.getStash().get(roleStashId, Role.class);
        Project project = Context.getStash().get(projectNameStashId, Project.class);
        User user = Context.getStash().get(userStashId, User.class);

        project.setIsPublic(false);
        user.addProject(project, Collections.singletonList(role));
        project.addUser(user, Collections.singletonList(role));
        //project.create();
    }
}