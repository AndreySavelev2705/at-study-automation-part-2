package at.study.automation.tests.redmine_auto_tests;

import at.study.automation.api.client.RestApiClient;
import at.study.automation.api.client.RestMethod;
import at.study.automation.api.client.RestRequest;
import at.study.automation.api.client.RestResponse;
import at.study.automation.api.dto.users.UserDto;
import at.study.automation.api.dto.users.UserInfoDto;
import at.study.automation.api.rest_assured.GsonProvider;
import at.study.automation.api.rest_assured.RestAssuredClient;
import at.study.automation.api.rest_assured.RestAssuredRequest;
import at.study.automation.db.requests.UserRequests;
import at.study.automation.model.user.Token;
import at.study.automation.model.user.User;

import at.study.automation.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.LocalDateTime;
import java.util.Collections;

import static at.study.automation.api.rest_assured.GsonProvider.GSON;

public class CrudUserByAdminTest {
    private RestApiClient apiClient;
    UserInfoDto dto;
    User userForCreating;
    RestResponse response;

    @BeforeMethod
    public void prepareFixtures() {

        User admin = new User() {{
            setIsAdmin(true);
            setTokens(Collections.singletonList(new Token(this)));
        }}.create();

        userForCreating = new User();

        dto = new UserInfoDto(
                new UserDto()
                        .setLogin(userForCreating.getLogin())
                        .setLastName(userForCreating.getLastName())
                        .setFirstName(userForCreating.getFirstName())
                        .setMail("savtestemail5@mail.ru")
                        .setPassword(userForCreating.getPassword())
                        .setStatus(2)
        );

        apiClient = new RestAssuredClient(admin);
    }

    @Test(description = "Создание, изменение, получение, удаление пользователя. Администратор системы.")
    public void crudTest() {
        response = apiClient.execute(creationUser(dto));
        firstStep(response);

        response = apiClient.execute(creationUser(dto));
        secondStep(response);

        response = apiClient.execute(creationUser(changedDto(dto)));
        thirdStep(response);
    }

    private RestRequest creationUser(UserInfoDto dto) {
        String body = GSON.toJson(dto);
        return new RestAssuredRequest(RestMethod.POST, "users.json", null, null, body);
    }

    private RestRequest getUser(UserInfoDto dto) {
        String body = GSON.toJson(dto);
        return new RestAssuredRequest(RestMethod.GET, "/users/" + dto.getUser().getId() + ".json", null, null, null);
    }

    private RestRequest updateUser(UserInfoDto dto) {
        String body = GSON.toJson(dto);
        return  new RestAssuredRequest(RestMethod.PUT, "/users/" + dto.getUser().getId() + ".json", null, null, null);
    }

    private RestRequest DeleteUser(UserInfoDto dto) {
        String body = GSON.toJson(dto);
        return new RestAssuredRequest(RestMethod.DELETE, "/users/" + dto.getUser().getId() + ".json", null, null, null);
    }

    private UserDto getUserFromResponse (RestResponse response) {
        return response.getPayload(UserInfoDto.class).getUser();
    }

    private UserInfoDto changedDto(UserInfoDto dto) {
        UserInfoDto newDto = dto;

        newDto.getUser()
                .setPassword("kjk3");

        return newDto;
    }

    private void firstStep(RestResponse response) {
        Assert.assertEquals(response.getStatusCode(), 201);

        Assert.assertNotNull(getUserFromResponse(response).getId());
        Assert.assertTrue(getUserFromResponse(response).getId() > 0);

        Assert.assertNotNull(getUserFromResponse(response).getLogin());
        Assert.assertEquals(getUserFromResponse(response).getLogin(), dto.getUser().getLogin());

        Assert.assertNotNull(getUserFromResponse(response).getIsAdmin());
        Assert.assertEquals(getUserFromResponse(response).getIsAdmin(), Boolean.FALSE);

        Assert.assertNotNull(getUserFromResponse(response).getFirstName());
        Assert.assertEquals(getUserFromResponse(response).getFirstName(), dto.getUser().getFirstName());

        Assert.assertNotNull(getUserFromResponse(response).getLastName());
        Assert.assertEquals(getUserFromResponse(response).getLastName(), dto.getUser().getLastName());

        Assert.assertNotNull(getUserFromResponse(response).getMail());
        Assert.assertEquals(getUserFromResponse(response).getMail(), dto.getUser().getMail());

        Assert.assertNotNull(getUserFromResponse(response).getCreatedOn());

        Assert.assertNull(getUserFromResponse(response).getLastLoginOn());

        Assert.assertNotNull(getUserFromResponse(response).getApiKey());

        Assert.assertNotNull(getUserFromResponse(response).getStatus());
        Assert.assertEquals(getUserFromResponse(response).getStatus().intValue(), dto.getUser().getStatus().intValue());

        User user = new UserRequests().read(getUserFromResponse(response).getId());
        Assert.assertEquals(getUserFromResponse(response).getId(), user.getId());
    }

    private void secondStep (RestResponse response) {
        Assert.assertEquals(response.getStatusCode(), 422);
    }

    private void thirdStep (RestResponse response) {
        Assert.assertEquals(response.getStatusCode(), 422);
    }
}
