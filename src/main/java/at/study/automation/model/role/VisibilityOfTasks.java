package at.study.automation.model.role;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum VisibilityOfTasks {
    ALL("all"),
    DEFAULT("default"),
    OWN("own");

    public final String visibilityLevel;
}
