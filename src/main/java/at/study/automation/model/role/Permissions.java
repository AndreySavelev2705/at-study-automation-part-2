package at.study.automation.model.role;

import lombok.AllArgsConstructor;

import java.util.TreeMap;

@AllArgsConstructor
public enum Permissions {
    ADD_PROJECT(true),
    EDIT_PROJECT(true),
    CLOSE_PROJECT(true),
    SELECT_PROJECT_MODULES(true),
    MANAGE_MEMBERS(true),
    MANAGE_VERSIONS(true),
    ADD_SUBPROJECTS(true),
    MANAGE_PUBLIC_QUERIES(true),
    SAVE_QUERIES(true),
    VIEW_MESSAGES(true),
    ADD_MESSAGES(true),
    EDIT_MESSAGES(true),
    EDIT_OWN_MESSAGES(true),
    DELETE_OWN_MESSAGES(true),
    DELETE_MESSAGES(true),
    MANAGE_BOARDS(true),
    VIEW_CALENDAR(true),
    VIEW_DOCUMENTS(true),
    ADD_DOCUMENTS(true),
    EDIT_DOCUMENTS(true),
    DELETE_DOCUMENTS(true),
    VIEW_FILES(true),
    MANAGE_FILES(true),
    VIEW_GANTT(true),
    VIEW_ISSUES(true),
    ADD_ISSUES(true),
    EDIT_ISSUES(true),
    EDIT_OWN_ISSUES(true),
    COPY_ISSUES(true),
    MANAGE_ISSUE_RELATIONS(true),
    MANAGE_SUBTASKS(true),
    SET_ISSUES_PRIVATE(true),
    SET_OWN_ISSUES_PRIVATE(true),
    ADD_ISSUE_NOTES(true),
    EDIT_ISSUE_NOTES(true),
    EDIT_OWN_ISSUE_NOTES(true),
    VIEW_PRIVATE_NOTES(true),
    SET_NOTES_PRIVATE(true),
    DELETE_ISSUES(true),
    VIEW_ISSUE_WATCHERS(true),
    ADD_ISSUE_WATCHERS(true),
    DELETE_ISSUE_WATCHERS(true),
    IMPORT_ISSUES(true),
    MANAGE_CATEGORIES(true),
    VIEW_NEWS(true),
    MANAGE_NEWS(true),
    COMMENT_NEWS(true),
    VIEW_CANGESETS(true),
    BROWSE_REPOSITORY(true),
    COMMIT_ACCESS(true),
    MANAGE_RELATED_ISSUES(true),
    MANAGE_REPOSITORY(true),
    VIEW_TIME_ENTRIES(true),
    LOG_TIME(true),
    EDIT_TIME_ENTRIES(true),
    EDIT_OWN_TIME_ENTRIES(true),
    MANAGE_PROJECT_ACTIVITIES(true),
    LOG_TIME_FOR_OTHER_USERS(true),
    IMPORT_TIME_ENTRIES(true),
    VIEW_WIKI_PAGES(true),
    VIEW_WIKI_EDITS(true),
    EXPORT_WIKI_PAGES(true),
    EDIT_WIKI_PAGES(true),
    RENAME_WIKI_PAGES(true),
    DELETE_WIKI_PAGES(true),
    DELETE_WIKI_PAGES_ATTACHMENTS(true),
    PROTECT_WIKI_PAGES(true),
    MANAGE_WIKI(true);
    
    public boolean permissionValue;

    private static final TreeMap<String, Boolean> VALUES;

    static {
        VALUES = new TreeMap<>();

        for (Permissions permission : Permissions.values()) {
            VALUES.put(permission.name(), permission.permissionValue);
        }
    }

    public static TreeMap<String, Boolean> getPermissions() {
        return VALUES;
    }
}
