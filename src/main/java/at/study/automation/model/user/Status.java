package at.study.automation.model.user;

import lombok.AllArgsConstructor;

import java.util.Arrays;

@AllArgsConstructor
public enum Status {
    UNREGISTERED(0),
    ACTIVE(1),
    UNACCEPTED(2),
    LOCKED(3);

    public final int statusCode;

    public static Status getStatusByStatusCode(Integer statusCode) {

        return Arrays.stream(Status.values())
                .filter(status -> statusCode == status.statusCode)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Статус не найден."));

    }
}
