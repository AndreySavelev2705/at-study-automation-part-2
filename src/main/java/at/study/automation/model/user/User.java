package at.study.automation.model.user;

import at.study.automation.model.Creatable;
import at.study.automation.model.CreatableEntity;
import at.study.automation.model.project.Project;
import at.study.automation.model.role.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static at.study.automation.utils.StringUtils.randomEnglishString;
import static at.study.automation.utils.StringUtils.randomHexString;
import static org.apache.commons.codec.digest.DigestUtils.sha1Hex;

@NoArgsConstructor
@Setter
@Getter
public class User extends CreatableEntity implements Creatable<User> {

    private String login = "SavelevAutoLogin";
    private String password = "lqaz@WSX";
    private String sail = randomHexString(32);
    private String hashedPassword = hashPassword();
    private String firstName = "SavelevAutoF" + randomEnglishString(10);
    private String lastName = "SavelevAutoL" + randomEnglishString(10);
    private Boolean isAdmin = false;
    private Status status = Status.ACTIVE;
    private LocalDateTime lastLoginOn;
    private Language language = Language.RUSSIAN;
    private String authSourceId;
    private String type = "User";
    private String identityUrl;
    private MailNotification mailNotification = MailNotification.NONE;
    private Boolean mustChangePassword = false;
    private LocalDateTime passwordChangedOn;
    private List<Token> tokens = new ArrayList<>();
    private List<Email> emails = new ArrayList<>();

    /**
     * Создает хэшированный пароль, используя библиотеку "commons-codec", на основе содержимого полей @salt и @password,
     * что подразумивается алгоритмом кодирования системы Redmine.
     *
     * @return строка, содержащая
     */
    public void setPassword(String password) {
        this.password = password;
        this.hashedPassword = hashPassword();
    }

    private String hashPassword() {
        return sha1Hex(sail + sha1Hex(password));
    }

    @Override
    public User create() {
        // TODO: Реализовать с помощью SQL-Запроса
        tokens.forEach(Token::create);
        emails.forEach(Email::create);
        throw new UnsupportedOperationException();
    }

    public void addProject(Project project, List<Role> roles) {
        // TODO: Реализовать с помощью SQL-Запроса
    }
}
