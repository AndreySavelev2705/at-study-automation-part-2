package at.study.automation.model.project;

import at.study.automation.model.Creatable;
import at.study.automation.model.CreatableEntity;
import at.study.automation.utils.StringUtils;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@NoArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
public class Project extends CreatableEntity implements Creatable<Project> {
    private String name = "Savelev_default_project_for_testing" + StringUtils.randomEnglishString(5);
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

    @Override
    public Project create() {
        // TODO: Реализовать с помощью SQL-Запроса
        return null;
    }
}
