package at.study.automation.model.role;

import lombok.AllArgsConstructor;

import java.util.ArrayList;

@AllArgsConstructor
public enum Settings {

    VIEW_ISSUES(1),
    ADD_ISSUES(1),
    EDIT_ISSUES(1),
    ADD_ISSUE_NOTES(1),
    DELETE_ISSUES(1);

    public Integer settingValue;

    private static final ArrayList<String> VALUES;

    static {
        VALUES = new ArrayList<>();

        for (Settings setting : Settings.values()) {
            VALUES.add(setting.name());
        }
    }

    public static ArrayList<String> getSettings() {
        return VALUES;
    }
}
