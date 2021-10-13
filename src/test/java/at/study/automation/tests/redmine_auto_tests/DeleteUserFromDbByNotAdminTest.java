package at.study.automation.tests.redmine_auto_tests;

import at.study.automation.api.client.RestApiClient;
import at.study.automation.api.client.RestMethod;
import at.study.automation.api.client.RestRequest;
import at.study.automation.api.client.RestResponse;
import at.study.automation.api.rest_assured.RestAssuredClient;
import at.study.automation.api.rest_assured.RestAssuredRequest;
import at.study.automation.db.requests.UserRequests;
import at.study.automation.model.user.Token;
import at.study.automation.model.user.User;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Collections;

public class DeleteUserFromDbByNotAdminTest {

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
    public void deleteUserFromDbByNotAdminTest() {
        deleteUserFromDb(user.getId());
        deleteUserWithApiKeyFromDb(notAdmin.getId());
    }

    private void deleteUserWithApiKeyFromDb(Integer userId) {
        RestRequest request = new RestAssuredRequest(RestMethod.DELETE, String.format(userEndpoint, userId), null, null, null);

        RestResponse response = apiClient.execute(request);

        User userFromDb = new UserRequests().read(userId);

        Assert.assertEquals(response.getStatusCode(), 403);
        Assert.assertNotNull(userFromDb.getId());
    }

    private void deleteUserFromDb(Integer userId) {
        RestRequest request = new RestAssuredRequest(RestMethod.DELETE, String.format(userEndpoint, userId), null, null, null);;

        RestResponse response = apiClient.execute(request);

        User userFromDb = new UserRequests().read(userId);

        Assert.assertEquals(response.getStatusCode(), 403);
        Assert.assertNotNull(userFromDb.getId());
    }
}
