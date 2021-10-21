package at.study.automation.tests.redmine_auto_tests;

import at.study.automation.api.client.RestApiClient;
import at.study.automation.api.client.RestMethod;
import at.study.automation.api.client.RestRequest;
import at.study.automation.api.client.RestResponse;
import at.study.automation.api.dto.users.UserDto;
import at.study.automation.api.dto.users.UserInfoDto;
import at.study.automation.api.rest_assured.RestAssuredClient;
import at.study.automation.api.rest_assured.RestAssuredRequest;
import at.study.automation.model.user.Token;
import at.study.automation.model.user.User;
import at.study.automation.utils.StringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;

import static at.study.automation.api.rest_assured.GsonProvider.GSON;

public class CreateUserByNotAdminTest {

    private RestApiClient apiClient;
    private UserInfoDto dto;
    private final String USER_ENDPOINT = "users.json";

    @BeforeMethod
    public void prepareFixtures() {
        User notAdmin = new User() {{
            setTokens(Collections.singletonList(new Token(this)));
        }}.create();

        dto = new UserInfoDto(
                new UserDto()
                        .setLogin("SavelevAutoLogin" + StringUtils.randomEnglishString(10))
                        .setLastName("SavelevAuto" + StringUtils.randomEnglishString(6))
                        .setFirstName("SavelevAuto" + StringUtils.randomEnglishString(8))
                        .setMail(StringUtils.randomEmail())
                        .setPassword(StringUtils.randomEnglishString(8))
                        .setStatus(1)
        );

        apiClient = new RestAssuredClient(notAdmin);
    }

    @Test
    public void createUserTest() {
        RestRequest request = new RestAssuredRequest(RestMethod.POST, USER_ENDPOINT, null, null, GSON.toJson(dto));

        RestResponse response = apiClient.execute(request);

        Assert.assertEquals(response.getStatusCode(), 403);
    }
}
