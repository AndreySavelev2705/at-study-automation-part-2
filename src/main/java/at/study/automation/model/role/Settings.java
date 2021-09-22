package at.study.automation.model.role;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

@AllArgsConstructor
public enum Settings {

    VIEW_ISSUES(1),
    ADD_ISSUES(1),
    EDIT_ISSUES(1),
    ADD_ISSUE_NOTES(1),
    DELETE_ISSUES(1);

    public Integer settingValue;

    private static final TreeMap<String, Integer> PERMISSIONS_ALL_TRACKERS;
    private static final TreeMap<String, Integer> PERMISSIONS_TRACKER_IDS;
    private static final ArrayList<Map<String, Integer>> SETTINGS;

    static {
        PERMISSIONS_ALL_TRACKERS = new TreeMap<>();
        PERMISSIONS_TRACKER_IDS = new TreeMap<>();

        for (Settings setting : Settings.values()) {
            PERMISSIONS_ALL_TRACKERS.put(setting.name(), setting.settingValue);
            PERMISSIONS_TRACKER_IDS.put(setting.name(), null);
        }

        SETTINGS = new ArrayList<>();
        SETTINGS.add(PERMISSIONS_ALL_TRACKERS);
        SETTINGS.add(PERMISSIONS_TRACKER_IDS);
    }

    public static ArrayList<Map<String, Integer>> getSettings() {
        return SETTINGS;
    }
}
