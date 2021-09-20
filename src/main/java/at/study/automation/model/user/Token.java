package at.study.automation.model.user;

import at.study.automation.model.Creatable;
import at.study.automation.model.CreatableEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static at.study.automation.utils.StringUtils.randomHexString;

@NoArgsConstructor
@Setter
@Getter
public class Token extends CreatableEntity implements Creatable<Token> {

    private Integer userId;
    private TokenType action = TokenType.API;
    private String value = randomHexString(40);

    public enum TokenType {
        SESSION,
        API,
        FEEDS
    }

    @Override
    public Token create() {
        // TODO: Реализовать с помощью SQL-Запроса
        throw new UnsupportedOperationException();
    }
}
