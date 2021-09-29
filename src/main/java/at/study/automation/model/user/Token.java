package at.study.automation.model.user;

import at.study.automation.db.requests.TokenRequests;
import at.study.automation.model.Creatable;
import at.study.automation.model.CreatableEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import static at.study.automation.utils.StringUtils.randomHexString;

@Setter
@Getter
@Accessors(chain = true)
public class Token extends CreatableEntity implements Creatable<Token> {

    private Integer userId;
    private TokenType action = TokenType.API;
    private String value = randomHexString(40);

    public enum TokenType {
        SESSION,
        API,
        FEEDS
    }

    public Token(User user) {
        this.userId = user.getId();
        user.getTokens().add(this);
    }

    @Override
    public Token create() {
        new TokenRequests().create(this);
        return this;
    }
}
