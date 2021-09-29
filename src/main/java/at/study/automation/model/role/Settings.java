package at.study.automation.model.role;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public enum Settings {

    VIEW_ISSUES(),
    ADD_ISSUES(),
    EDIT_ISSUES(),
    ADD_ISSUE_NOTES(),
    DELETE_ISSUES();

    public final String settingValue = "--- !ruby/hash:ActiveSupport::HashWithIndifferentAccess\n" +
            "permissions_all_trackers: !ruby/hash:ActiveSupport::HashWithIndifferentAccess\n" +
            "  view_issues: '1'\n" +
            "  add_issues: '1'\n" +
            "  edit_issues: '1'\n" +
            "  add_issue_notes: '1'\n" +
            "  delete_issues: '1'\n" +
            "permissions_tracker_ids: !ruby/hash:ActiveSupport::HashWithIndifferentAccess\n" +
            "  view_issues: []\n" +
            "  add_issues: []\n" +
            "  edit_issues: []\n" +
            "  add_issue_notes: []\n" +
            "  delete_issues: []\n";

}
