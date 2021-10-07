package at.study.automation.tests.redmine_auto_tests;

import at.study.automation.api.client.RestApiClient;
import at.study.automation.api.client.RestMethod;
import at.study.automation.api.client.RestRequest;
import at.study.automation.api.client.RestResponse;
import at.study.automation.api.dto.users.UserDto;
import at.study.automation.api.dto.users.UserInfoDto;
import at.study.automation.api.rest_assured.RestAssuredClient;
import at.study.automation.api.rest_assured.RestAssuredRequest;
import at.study.automation.db.requests.UserRequests;
import at.study.automation.model.user.Status;
import at.study.automation.model.user.Token;
import at.study.automation.model.user.User;
import at.study.automation.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;

import static at.study.automation.api.rest_assured.GsonProvider.GSON;

public class CrudUserByAdminTest {
    private RestApiClient apiClient;
    private RestRequest requestPost;
    private RestRequest requestGet;
    private RestRequest requestPut;
    private RestRequest requestDelete;
    private User admin;
    private User createdUser;

    @BeforeMethod
    public void prepareFixtures() {

        admin = new User() {{
            setIsAdmin(true);
            setTokens(Collections.singletonList(new Token(this)));
        }}.create();

        createdUser = new User() {{
            setStatus(Status.UNACCEPTED);
        }}.create();

        apiClient = new RestAssuredClient(admin);

        User userForCreating = new User();

        UserInfoDto dto = new UserInfoDto(
                new UserDto()
                        .setLogin(userForCreating.getLogin())
                        .setLastName(userForCreating.getLastName())
                        .setFirstName(userForCreating.getFirstName())
                        .setMail(StringUtils.randomEmail())
                        .setPassword(userForCreating.getPassword())
                        .setStatus(2)
        );

        String body = GSON.toJson(dto);

        requestPost = new RestAssuredRequest(RestMethod.POST, "users.json", null, null, body);
        requestGet = new RestAssuredRequest(RestMethod.GET, "/users/" + createdUser.getId() + ".json", null, null, null);
        requestPut = new RestAssuredRequest(RestMethod.PUT, "/users/" + createdUser.getId() + ".json", null, null, null);
        requestDelete = new RestAssuredRequest(RestMethod.DELETE, "/users/" + createdUser.getId() + ".json", null, null, null);
    }

    @Test(description = "Создание, изменение, получение, удаление пользователя. Администратор системы: Шаг 1")
    public void firstStepTest() {
        RestResponse response = apiClient.execute(requestPost);

        Assert.assertEquals(response.getStatusCode(), 201);

        // TODO: доработать проверку наличая id в ответе

        Integer id = response.getPayload(UserInfoDto.class).getUser().getId();

        User user = new UserRequests().read(id);

        Assert.assertEquals(id, user.getId());
    }

}
