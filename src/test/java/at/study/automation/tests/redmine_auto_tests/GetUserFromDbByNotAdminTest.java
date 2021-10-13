package at.study.automation.tests.redmine_auto_tests;

import at.study.automation.api.client.RestApiClient;
import at.study.automation.api.client.RestMethod;
import at.study.automation.api.client.RestRequest;
import at.study.automation.api.client.RestResponse;
import at.study.automation.api.dto.users.UserInfoDto;
import at.study.automation.api.rest_assured.RestAssuredClient;
import at.study.automation.api.rest_assured.RestAssuredRequest;
import at.study.automation.model.user.Token;
import at.study.automation.model.user.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;

public class GetUserFromDbByNotAdminTest {

    private RestApiClient apiClient;
    private final String userEndpoint = "/users/%d.json";
    private User notAdmin;
    private User user;

    @BeforeMethod
    public void prepareFixtures() {
        notAdmin = new User() {{
            setTokens(Collections.singletonList(new Token(this)));
        }}.create();

        user = new User().create();

        apiClient = new RestAssuredClient(notAdmin);
    }

    @Test
    public void getUserFromDbByNotAdminTest() {
        getUserWithApiKeyFromDb(notAdmin.getId());
        getUserFromDb(user.getId());
    }

    private void getUserWithApiKeyFromDb(Integer userId) {
        RestRequest request = new RestAssuredRequest(RestMethod.GET, String.format(userEndpoint, userId), null, null, null);

        RestResponse response = apiClient.execute(request);

        UserInfoDto dto = response.getPayload(UserInfoDto.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertFalse(dto.getUser().getIsAdmin());
        Assert.assertTrue(dto.getUser().getApiKey().matches("^[0-9a-fA-F]*$"));
    }

    private void getUserFromDb(Integer userId) {
        RestRequest request = new RestAssuredRequest(RestMethod.GET, String.format(userEndpoint, userId), null, null, null);

        RestResponse response = apiClient.execute(request);

        UserInfoDto dto = response.getPayload(UserInfoDto.class);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertNull(dto.getUser().getIsAdmin());
        Assert.assertNull(dto.getUser().getApiKey());
    }
}
