package at.study.automation.model.user;

import lombok.AllArgsConstructor;

import java.util.Arrays;
import java.util.stream.Stream;

@AllArgsConstructor
public enum Status {
    UNREGISTERED(0, "Не зарегистрирован"),
    ACTIVE(1, "Активен"),
    UNACCEPTED(2, "Не подтвержден"),
    LOCKED(3, "Заблокирован");

    public final int statusCode;
    private final String description;

    public static Status of(Integer statusCode) {

        return Arrays.stream(Status.values())
                .filter(status -> statusCode == status.statusCode)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Статус не найден."));

    }

    public static Status of(String description) {
        return Stream.of(values())
                .filter(status -> description.equals(status.description))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Не найден объект Status с описание " + description));

    }
}
