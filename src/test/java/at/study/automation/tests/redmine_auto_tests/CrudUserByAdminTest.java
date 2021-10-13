package at.study.automation.tests.redmine_auto_tests;

import at.study.automation.api.client.RestApiClient;
import at.study.automation.api.client.RestMethod;
import at.study.automation.api.client.RestRequest;
import at.study.automation.api.client.RestResponse;
import at.study.automation.api.dto.errors.ErrorInfoDto;
import at.study.automation.api.dto.users.UserDto;
import at.study.automation.api.dto.users.UserInfoDto;
import at.study.automation.api.rest_assured.RestAssuredClient;
import at.study.automation.api.rest_assured.RestAssuredRequest;
import at.study.automation.db.requests.UserRequests;
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
    private UserInfoDto dto;
    private final String userEndpoint = "/users/%d.json";

    @BeforeMethod
    public void prepareFixtures() {

        User admin = new User() {{
            setIsAdmin(true);
            setTokens(Collections.singletonList(new Token(this)));
        }}.create();

        dto = new UserInfoDto(
                new UserDto()
                        .setLogin("SavelevAutoLogin" + StringUtils.randomEnglishString(10))
                        .setLastName("SavelevAuto" + StringUtils.randomEnglishString(6))
                        .setFirstName("SavelevAuto" + StringUtils.randomEnglishString(8))
                        .setMail(StringUtils.randomEmail())
                        .setPassword(StringUtils.randomEnglishString(8))
                        .setStatus(2)
        );

        apiClient = new RestAssuredClient(admin);
    }

    @Test(description = "Создание, изменение, получение, удаление пользователя. Администратор системы.")
    public void crudUserTest() {
        Integer createdUserId = createAndValidateUser();

        createAndValidateDuplicatedUser();
        createAndValidateUserWithShortPassword();
        updateAndValidateUserWithNewStatus(createdUserId);
        getUserFromDbWithNewStatus(createdUserId);
        deleteUserFromDb(createdUserId);
        checkUserWasDeleted(createdUserId);
    }

    private Integer createAndValidateUser() {
        RestRequest request = new RestAssuredRequest(RestMethod.POST, "users.json", null, null, GSON.toJson(dto));

        RestResponse response = apiClient.execute(request);

        UserDto userFromResponse = response.getPayload(UserInfoDto.class).getUser();

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertTrue(userFromResponse.getId() > 0);
        Assert.assertEquals(userFromResponse.getLogin(), dto.getUser().getLogin());
        Assert.assertEquals(userFromResponse.getIsAdmin(), Boolean.FALSE);
        Assert.assertEquals(userFromResponse.getFirstName(), dto.getUser().getFirstName());
        Assert.assertEquals(userFromResponse.getLastName(), dto.getUser().getLastName());
        Assert.assertEquals(userFromResponse.getMail(), dto.getUser().getMail());
        Assert.assertNotNull(userFromResponse.getCreatedOn());
        Assert.assertNull(userFromResponse.getLastLoginOn());
        Assert.assertTrue(userFromResponse.getApiKey().matches("^[0-9a-fA-F]*$"));
        Assert.assertEquals(userFromResponse.getStatus().intValue(), dto.getUser().getStatus().intValue());
        User user = new UserRequests().read(userFromResponse.getId());
        Assert.assertEquals(userFromResponse.getId(), user.getId());

        return userFromResponse.getId();
    }

    private void createAndValidateDuplicatedUser() {
        RestRequest request = new RestAssuredRequest(RestMethod.POST, "users.json", null, null, GSON.toJson(dto));

        RestResponse response = apiClient.execute(request);

        Assert.assertEquals(response.getStatusCode(), 422);

        ErrorInfoDto errorInfoDto = response.getPayload(ErrorInfoDto.class);

        Assert.assertEquals(errorInfoDto.getErrors().get(0), "Email уже существует");
        Assert.assertEquals(errorInfoDto.getErrors().get(1), "Пользователь уже существует");
    }

    private void createAndValidateUserWithShortPassword() {
        String currentPassword = dto.getUser().getPassword();

        dto.getUser().setPassword("kj3k");

        RestRequest request = new RestAssuredRequest(RestMethod.POST, "users.json", null, null, GSON.toJson(dto));

        RestResponse response = apiClient.execute(request);
        ErrorInfoDto errorInfoDto = response.getPayload(ErrorInfoDto.class);

        Assert.assertEquals(response.getStatusCode(), 422);
        Assert.assertEquals(errorInfoDto.getErrors().get(0), "Email уже существует");
        Assert.assertEquals(errorInfoDto.getErrors().get(1), "Пользователь уже существует");
        Assert.assertEquals(errorInfoDto.getErrors().get(2), "Пароль недостаточной длины (не может быть меньше 8 символа)");

        dto.getUser().setPassword(currentPassword);
    }

    private void updateAndValidateUserWithNewStatus(Integer userId) {
        dto.getUser().setStatus(1);

        RestRequest request = new RestAssuredRequest(RestMethod.PUT, String.format(userEndpoint, userId), null, null, GSON.toJson(dto));

        RestResponse response = apiClient.execute(request);

        Assert.assertEquals(response.getStatusCode(), 204);

        User user = new UserRequests().read(userId);
        Assert.assertEquals(user.getStatus().statusCode, dto.getUser().getStatus().intValue());
    }

    private void getUserFromDbWithNewStatus(Integer userId) {
        RestRequest request = new RestAssuredRequest(RestMethod.GET, String.format(userEndpoint, userId), null, null, null);

        RestResponse response = apiClient.execute(request);

        Assert.assertEquals(response.getStatusCode(), 200);

        User user = new UserRequests().read(userId);
        Assert.assertEquals(user.getStatus().statusCode, dto.getUser().getStatus().intValue());
    }

    private void deleteUserFromDb(Integer userId) {
        RestRequest request = new RestAssuredRequest(RestMethod.DELETE, String.format(userEndpoint, userId), null, null, null);

        RestResponse response = apiClient.execute(request);

        Assert.assertEquals(response.getStatusCode(), 204);
        Assert.assertNull(new UserRequests().read(userId));
    }

    private void checkUserWasDeleted(Integer userId) {
        RestRequest request= new RestAssuredRequest(RestMethod.DELETE, String.format(userEndpoint, userId), null, null, null);

        RestResponse response = apiClient.execute(request);

        Assert.assertEquals(response.getStatusCode(), 404);
    }
}
