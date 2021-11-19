package at.study.automation.cucumber.validators;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class UserParametersValidator {

    public static void validateUserParameters(Set<String> keys) {
        // TODO: дополнить всеми возможными значениями параметров у пользователя
        List<String> allowedKeys = Arrays.asList(
                "Администратор",
                "Статус",
                "Уведомления о новых событиях",
                "E-Mail",
                "Api-ключ"
        );

        boolean allKeysAreValid = allowedKeys.containsAll(keys);

        if (!allKeysAreValid) {
            throw new IllegalArgumentException("Среди переданных параметров пользователя есть недопустимые параметры");
        }
    }
}
