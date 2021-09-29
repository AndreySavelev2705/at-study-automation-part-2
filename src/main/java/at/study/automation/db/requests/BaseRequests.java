package at.study.automation.db.requests;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class BaseRequests {

    // Преобразование Timestamp в объект LocalDateTime
    protected LocalDateTime toLocalDate(Object timestamp) {
        Timestamp ts = (Timestamp) timestamp;
        return  ts.toLocalDateTime();
    }
}
