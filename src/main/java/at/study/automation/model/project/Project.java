package at.study.automation.model.project;

import at.study.automation.db.requests.ProjectRequests;
import at.study.automation.model.Creatable;
import at.study.automation.model.CreatableEntity;
import at.study.automation.model.role.Role;
import at.study.automation.model.user.User;
import at.study.automation.utils.StringUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@NoArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
public class Project extends CreatableEntity implements Creatable<Project> {
    private String name = "Savelev_project_for_testing" + StringUtils.randomEnglishString(5);
    private String description;
    private String homepage;
    private Boolean isPublic = true;
    private Integer parentId = 2705;
    private String identifier = name;
    private Status status = Status.OPENED;
    private Integer lft = 27;
    private Integer rgt = 5;
    private Boolean inheritMembers = true;
    private Integer defaultVersionId = 19;
    private Integer defaultAssignedToId = 95;
    private Map<User, List<Role>> usersAndRolesOfProject = new HashMap<>();

    @Override
    public Project create() {
        new ProjectRequests().create(this);
        return this;
    }

    public void addUser(User user, List<Role> roles) {
        // TODO: Реализовать с помощью SQL-Запроса
        usersAndRolesOfProject.put(user, roles);
    }
}
