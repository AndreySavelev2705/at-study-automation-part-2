package steps;

import at.study.automation.context.Context;
import at.study.automation.cucumber.validators.ProjectParametersValidator;
import at.study.automation.cucumber.validators.RoleParametersValidator;
import at.study.automation.cucumber.validators.UserParametersValidator;
import at.study.automation.model.project.Project;
import at.study.automation.model.role.Permission;
import at.study.automation.model.role.Role;
import at.study.automation.model.user.*;
import cucumber.api.java.ru.Пусть;
import cucumber.api.java.ru.Также;
import io.cucumber.datatable.DataTable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class PrepareFixtureSteps {

    @Пусть("Имеется список E-Mail адресов \"(.+)\":")
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

    @Пусть("В системе существует роль \"(.+)\" с правами:")
    public void createRole(String roleStashId, List<String> permissions) {
        RoleParametersValidator.validateUserParameters(permissions);

        Role role = new Role();

        role.setPermissions(
                permissions.stream().map(description -> Permission.from(description)).collect(toList())
        );
        role.create();
        Context.getStash().put(roleStashId, role);
    }

    @Пусть("Список ролей \"(.+)\" содержит роли:")
    public void createRolesList(String rolesStashId, List<String> roleNameStashIds) {
        List<Role> roles = new ArrayList<>();

        for (String roleNameStashId : roleNameStashIds) {
            Role role = Context.getStash().get(roleNameStashId, Role.class);
            roles.add(role);
        }

        Context.getStash().put(rolesStashId, roles);
    }

    // TODO дописать валида2цию ключа передаваемого параметра проекта
    @Также("Существует проект \"(.+)\" с параметрами:")
    public void createProject(String projectStashId, Map<String, String> parameters) {
        ProjectParametersValidator.validateUserParameters(parameters.keySet());

        Project project;

        if (parameters.containsValue("false")) {
            project = new Project() {{
                setIsPublic(false);
            }}.create();
        } else {
            project = new Project().create();
        }

        Context.getStash().put(projectStashId, project);
    }

    @Также("Пользователь \"(.+)\" имеет доступ к проекту \"(.+)\" со списком ролей \"(.+)\"")
    public void linkingProjectAndUserAndRoles(String userStashId, String projectNameStashId, String roleStashId) {
        List<Role> roles = Context.getStash().get(roleStashId, List.class);
        Project project = Context.getStash().get(projectNameStashId, Project.class);
        User user = Context.getStash().get(userStashId, User.class);

        user.addProject(project, roles);
    }
}