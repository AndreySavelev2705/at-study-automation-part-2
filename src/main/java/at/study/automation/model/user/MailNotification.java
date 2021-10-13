package at.study.automation.model.user;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum MailNotification {
    ALL("О всех событиях в моих проектах"),
    ONLY_MY_EVENTS("Только для объектов, которые я отслекживаю или в которых учавствую"),
    ONLY_ASSIGNED("Только для объектов, которые я отслекживаю или которые мне назначены"),
    ONLY_OWNER("Только для объектов, которые я отслекживаю или для которых я владелец"),
    NONE("Нет событий");

    public final String description;

    public static MailNotification getEmailNotificationByDescription(String description) {
        for (MailNotification mailNotification : MailNotification.values()) {
            if (mailNotification.name().equals(description)) {
                return mailNotification;
            }
        }

        return Arrays.stream(MailNotification.values())
                .filter(mailNotification -> description.equals(mailNotification.description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Такой вариации событий для уведомления не существует."));
    }
}
