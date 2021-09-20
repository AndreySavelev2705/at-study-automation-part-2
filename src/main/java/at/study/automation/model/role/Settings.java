package at.study.automation.model.role;

import lombok.AllArgsConstructor;

import java.util.TreeMap;

@AllArgsConstructor
public enum Settings {

    VIEW_ISSUES(1),
    ADD_ISSUES(1),
    EDIT_ISSUES(1),
    ADD_ISSUE_NOTES(1),
    DELETE_ISSUES(1);

    public Integer settingValue;

    private static final TreeMap<String, Integer> VALUES;

    static {
        VALUES = new TreeMap<>();

        for (Settings setting : Settings.values()) {
            VALUES.put(setting.name(), setting.settingValue);
        }
    }

    public static TreeMap<String, Integer> getSettings() {
        return VALUES;
    }
}
