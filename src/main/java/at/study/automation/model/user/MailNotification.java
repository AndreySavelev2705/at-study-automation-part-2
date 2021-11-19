package at.study.automation.model.user;

import lombok.AllArgsConstructor;

import java.util.stream.Stream;

@AllArgsConstructor
public enum MailNotification {
    ALL("О всех событиях во всех моих проектах"),
    ONLY_MY_EVENTS("Только для объектов, которые я отслекживаю или в которых учавствую"),
    ONLY_ASSIGNED("Только для объектов, которые я отслекживаю или которые мне назначены"),
    ONLY_OWNER("Только для объектов, которые я отслекживаю или для которых я владелец"),
    NONE("Нет событий");

    public final String description;

    public static MailNotification of(String description) {

        return Stream.of(values())
                .filter(mailNotification -> mailNotification.description.equals(description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Не найден объект MailNotification с описанием " + description));
    }
}
