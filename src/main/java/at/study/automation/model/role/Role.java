package at.study.automation.model.role;

import at.study.automation.model.Creatable;
import at.study.automation.model.Entity;
import jdk.jfr.Description;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Setter
@Getter
public class Role extends Entity implements Creatable<Role> {
    private String name = "Savelev_default_role";
    private Integer position = 27051995;
    private Boolean assignable = true;
    private Builtin builtin = Builtin.CURRENT_ROLE;
    private List<String> permissions = Permissions.getPermissions();
    @Description("issuesVisibility")
    private VisibilityOfTasks visibilityOfTasks = VisibilityOfTasks.ALL;
    private UsersVisibility usersVisibility = UsersVisibility.ALL;
    private TimeEntriesVisibility timeEntriesVisibility = TimeEntriesVisibility.All;
    private Boolean allRolesManaged = true;
    private ArrayList<String> strings = Settings.getSettings();

    @Override
    public Role create() {
        // TODO: Реализовать с помощью SQL-Запроса
        return null;
    }
}
