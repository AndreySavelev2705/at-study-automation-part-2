package at.study.automation.cucumber.validators;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class ProjectParametersValidator {

    public static void validateUserParameters(Set<String> permissions) {
        List<String> allowedKeys = Arrays.asList(
                "Публичный"
        );

        boolean allKeysAreValid = allowedKeys.containsAll(permissions);

        if (!allKeysAreValid) {
            throw new IllegalArgumentException("Среди переданных разрешений для роли есть недопустимые параметры");
        }
    }
}
