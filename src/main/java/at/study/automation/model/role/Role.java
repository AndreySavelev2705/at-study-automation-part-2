package at.study.automation.model.role;

import at.study.automation.model.Creatable;
import at.study.automation.model.Entity;
import at.study.automation.utils.StringUtils;
import jdk.jfr.Description;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
public class Role extends Entity implements Creatable<Role> {
    private String name = "Savelev_default_role" + StringUtils.randomEnglishString(5);
    private Integer position;
    private Boolean assignable;
    private Builtin builtin;
    private List<Permission> permissions;
    @Description("issuesVisibility")
    private VisibilityOfTasks visibilityOfTasks = VisibilityOfTasks.ALL;
    private UsersVisibility usersVisibility = UsersVisibility.ALL;
    private TimeEntriesVisibility timeEntriesVisibility = TimeEntriesVisibility.ALL;
    private Boolean allRolesManaged = true;
    private List<Map<String, Integer>> settings;

    @Override
    public Role create() {
        // TODO: Реализовать с помощью SQL-Запроса
        return null;
    }

    @Test
    public void test() {
        Role role = new Role();
    }
}
