package at.study.automation.model.role;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TimeEntriesVisibility {
    All("Все трудозатраты"),
    ONLY_YOUR_OWN_LABOR_COSTS("Только собвственные трудозатраты");

    public final String timeEntriesVisibilityValue;
}
