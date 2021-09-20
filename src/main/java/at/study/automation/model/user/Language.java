package at.study.automation.model.user;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Language {
    RUSSIAN("ru"),
    ENGLISH("en");

    public final String languageCode;
}
