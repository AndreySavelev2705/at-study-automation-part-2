package at.study.automation.model.user;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Language {
    RUSSIAN("ru"),
    ENGLISH("en");

    public final String languageCode;

    public static Language getEnumByLanguageCode(String languageCode) {
        for (Language language : Language.values()) {
            if (languageCode.equals(language.languageCode)) {
                return language;
            }
        }

        throw new RuntimeException();
    }
}
