package at.study.automation.model.user;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MailNotification {
    ALL("О всех событиях в моих проектах"),
    ONLY_MY_EVENTS("Только для объектов, которые я отслекживаю или в которых учавствую"),
    ONLY_ASSIGNED("Только для объектов, которые я отслекживаю или которые мне назначены"),
    ONLY_OWNER("Только для объектов, которые я отслекживаю или для которых я владелец"),
    NONE("Нет событий");

    public final String description;

    public static MailNotification getEnumByDescription(String description) {
        for (MailNotification mailNotification : MailNotification.values()) {
            if (mailNotification.name().equals(description)) {
                return mailNotification;
            }
        }

        throw new RuntimeException();
    }
}
