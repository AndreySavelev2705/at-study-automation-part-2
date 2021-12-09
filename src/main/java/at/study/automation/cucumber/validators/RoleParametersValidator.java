package at.study.automation.cucumber.validators;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class RoleParametersValidator {

    public static void validateUserParameters(Set<String> keys) {
        List<String> allowedKeys = Arrays.asList(
                "Просмотр задач"
        );

        boolean allKeysAreValid = allowedKeys.containsAll(keys);

        if (!allKeysAreValid) {
            throw new IllegalArgumentException("Среди переданных разрешений для роли есть недопустимые параметры");
        }
    }
}
