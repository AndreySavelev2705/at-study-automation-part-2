package at.study.automation.model.user;

import at.study.automation.db.requests.UserRequests;
import at.study.automation.model.Creatable;
import at.study.automation.model.CreatableEntity;
import at.study.automation.model.Deleteable;
import at.study.automation.model.Updateable;
import at.study.automation.model.project.Project;
import at.study.automation.model.role.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static at.study.automation.utils.StringUtils.randomEnglishString;
import static at.study.automation.utils.StringUtils.randomHexString;
import static org.apache.commons.codec.digest.DigestUtils.sha1Hex;

@NoArgsConstructor
@Setter
@Getter
@Accessors(chain = true)
public class User extends CreatableEntity implements Creatable<User>, Updateable<User>, Deleteable<User> {

    private String login = "SavelevAutoLogin" + randomEnglishString(10);
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
    private Map<Project, List<Role>> projects = new HashMap<>();

    /**
     * Создает хэшированный пароль, используя библиотеку "commons-codec", на основе содержимого полей @salt и @password,
     * что подразумивается алгоритмом кодирования системы Redmine.
     *
     * @return строка, содержащая
     */
    public User setPassword(String password) {
        this.password = password;
        this.hashedPassword = hashPassword();
        return this;
    }

    private String hashPassword() {
        return sha1Hex(sail + sha1Hex(password));
    }

    /**
     * Метод создает пользователя и добавляет его в бд
     *
     * @return возвращает текущего пользователя
     */
    @Override
    public User create() {
        new UserRequests().create(this);

        tokens.forEach(t -> t.setUserId(id));
        tokens.forEach(Token::create);

        emails.forEach(e -> e.setUserId(id));
        emails.forEach(Email::create);

        return this;
    }

    /**
     * Метод удаляет пользователя из бд по его айдишнику
     *
     * @return возвращает текущего пользователя
     */
    @Override
    public User delete() {
        new UserRequests().delete(this.id);
        return this;
    }

    /**
     * Метод обновляет дпнные уже существующего в бд пользователя по его айди,
     * на основе даннх из переданного в параметрах метода
     *
     * @return возвращает текущего пользователя
     */
    @Override
    public User update() {
        new UserRequests().update(this.id, this);
        return this;
    }

    public void addProject(Project project, List<Role> roles) {
        // TODO: Реализовать с помощью SQL-Запроса
        projects.put(project, roles);
    }
}
