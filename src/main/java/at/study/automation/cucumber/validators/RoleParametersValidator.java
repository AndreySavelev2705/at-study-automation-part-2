package at.study.automation.cucumber.validators;

import java.util.Arrays;
import java.util.List;

public class RoleParametersValidator {

    public static void validateUserParameters(List<String> permissions) {
        List<String> allowedKeys = Arrays.asList(
                "Просмотр задач"
        );

        boolean allKeysAreValid = allowedKeys.containsAll(permissions);

        if (!allKeysAreValid) {
            throw new IllegalArgumentException("Среди переданных разрешений для роли есть недопустимые параметры");
        }
    }
}
