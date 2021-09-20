package at.study.automation.model.role;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum UsersVisibility {
    ALL("all"),
    MEMBERS_OF_VISIBLE_PROJECTS("members of visible projects");

    public final String visibilityValue;
}
