package at.study.automation.model.project;

import at.study.automation.db.requests.ProjectRequests;
import at.study.automation.db.requests.communications.AddToMembersRequests;
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
    private Integer parentId;
    private String identifier = name;
    private Status status = Status.OPENED;
    private Integer lft = 111;
    private Integer rgt = 222;
    private Boolean inheritMembers = true;
    private Integer defaultVersionId;
    private Integer defaultAssignedToId;
    private Map<User, List<Role>> members = new HashMap<>();

    @Override
    public Project create() {
        new ProjectRequests().create(this);
        for (User user : members.keySet()) {
            Integer memberId = new AddToMembersRequests().addMember(user.getId(), this.getId());

            for (List<Role> roles : members.values()) {
                roles.forEach(role -> new AddToMembersRequests().addMemberRoles(memberId, role.getId()));
            }
        }
        return this;
    }

    public void addUser(User user, List<Role> roles) {
        // TODO: Реализовать с помощью SQL-Запроса
        members.put(user,roles);
    }
}
