package at.study.automation.model.user;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Status {
    UNREGISTERED(0),
    ACTIVE(1),
    UNACCEPTED(2),
    LOCKED(3);

    public final int statusCode;

    public static Status getEnumByStatusCode(Integer statusCode) {
        for (Status status : Status.values()) {
            if (status.statusCode == statusCode) {
                return status;
            }
        }

        throw new RuntimeException();
    }
}
