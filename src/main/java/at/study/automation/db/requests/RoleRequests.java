package at.study.automation.db.requests;

import at.study.automation.db.connection.PostgresConnection;
import at.study.automation.model.role.Permission;
import at.study.automation.model.role.Role;
import io.qameta.allure.Step;

import java.util.List;

public class RoleRequests extends BaseRequests implements Create<Role>, Update<Role>, Delete<Role> {

    @Override
    @Step("Создание роли с набором пермишенов в бд")
    public void create(Role role) {
        String query = "INSERT INTO public.roles\n" +
                "(id, \"name\", \"position\", assignable, builtin, permissions, issues_visibility, " +
                "users_visibility, time_entries_visibility, all_roles_managed, settings)\n" +
                "VALUES(DEFAULT, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;\n";

        Integer id = (Integer) PostgresConnection.INSTANCE.executeQuery(
                query,
                role.getName(),
                role.getPosition(),
                role.getAssignable(),
                role.getBuiltin().builtinCode,
                convertPermissionsToString(role.getPermissions()),
                role.getVisibilityOfTasks().databaseValue,
                role.getUsersVisibility().visibilityValue,
                role.getTimeEntriesVisibility().timeEntriesVisibilityValue,
                role.getAllRolesManaged(),
                role.getSettings()
        ).get(0).get("id");
        role.setId(id);
    }

    @Override
    public void delete(Integer id) {
        String query = "DELETE FROM public.roles\n" +
                "WHERE id=?;\n";
        PostgresConnection.INSTANCE.executeQuery(query, id);
    }

    @Override
    public void update(Integer id, Role role) {
        String query = "UPDATE public.roles\n" +
                "SET \"name\"=?, \"position\"=?, assignable=?, builtin=?, " +
                "permissions=?, issues_visibility=?, users_visibility=?, " +
                "time_entries_visibility=?, all_roles_managed=?, settings=?\n" +
                "WHERE id=?;\n";

        PostgresConnection.INSTANCE.executeQuery(
                query,
                role.getName(),
                role.getPosition(),
                role.getAssignable(),
                role.getBuiltin().builtinCode,
                convertPermissionsToString(role.getPermissions()),
                role.getVisibilityOfTasks().databaseValue,
                role.getUsersVisibility().visibilityValue,
                role.getTimeEntriesVisibility().timeEntriesVisibilityValue,
                role.getAllRolesManaged(),
                role.getSettings(),
                id
        );
    }

    private String convertPermissionsToString(List<Permission> permissions) {
        StringBuilder sb = new StringBuilder();

        sb.append("---\n");

        permissions.forEach(permission -> sb.append("- :").append(permission.name().toLowerCase()).append("\n"));

        return sb.toString();
    }
}
