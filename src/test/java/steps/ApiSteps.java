package steps;

import at.study.automation.allure.AllureAssert;
import at.study.automation.api.client.RestApiClient;
import at.study.automation.api.client.RestMethod;
import at.study.automation.api.client.RestRequest;
import at.study.automation.api.client.RestResponse;
import at.study.automation.api.dto.users.UserDto;
import at.study.automation.api.dto.users.UserInfoDto;
import at.study.automation.api.rest_assured.RestAssuredRequest;
import at.study.automation.context.Context;
import at.study.automation.db.requests.EmailRequests;
import at.study.automation.db.requests.UserRequests;
import at.study.automation.model.user.Email;
import at.study.automation.model.user.Status;
import at.study.automation.model.user.User;
import at.study.automation.utils.StringUtils;
import cucumber.api.java.ru.Затем;

import java.util.Collections;

import static at.study.automation.allure.AllureAssert.*;
import static at.study.automation.allure.AllureUtils.*;
import static at.study.automation.api.rest_assured.GsonProvider.GSON;

public class ApiSteps {

    @Затем("Выполнение \"(.+)\" запроса через \"(.+)\" " +
            "на эндпоинт \"(.+)\" и получение ответа \"(.+)\" от сервера")
    public void executePostRequest(String requestType, String apiClientNameId, String userEndpointId, String responseId) {

        User user = new User(){{
            setEmails(Collections.singletonList(new Email()));
            setStatus(Status.UNACCEPTED);
        }};

        UserInfoDto dto = getUserInfoDto(user);

        RestRequest request = generatingRequest(
                new RestAssuredRequest(RestMethod.POST, userEndpointId, null, null, GSON.toJson(dto))
        );

        RestApiClient apiClient = Context.getStash().get(apiClientNameId, RestApiClient.class);

        RestResponse response = apiClient.execute(request);

        Context.getStash().put(responseId, response);
        Context.getStash().put(apiClientNameId, apiClient);
        Context.getStash().put(requestType, request);
    }

    @Затем("Cнова через api-клиент \"(.+)\" выполнить post \"(.+)\" запрос и проверить ответ \"(.+)\"")
    public void executePostRequest(String apiClientNameId, String requestType, String responseId){
        RestRequest request = Context.getStash().get(requestType, RestRequest.class);

        RestApiClient apiClient = Context.getStash().get(apiClientNameId, RestApiClient.class);

        RestResponse response = apiClient.execute(request);

        Context.getStash().put(responseId, response);
    }

    @Затем("Выполнение GET запроса через \"(.+)\" пользователем \"(.+)\"" +
            " на эндпоинт \"(.+)\" и получение ответа \"(.+)\" от сервера")
    public void executeGetRequest(String apiClientNameId, String userStashId, String userEndpointId, String responseId) {
        User user = Context.getStash().get(userStashId, User.class);

        RestRequest request = generatingRequest(
            new RestAssuredRequest(RestMethod.GET, String.format(userEndpointId, user.getId()), null, null, null)
        );

        RestApiClient apiClient = Context.getStash().get(apiClientNameId, RestApiClient.class);

        RestResponse response = apiClient.execute(request);

        Context.getStash().put(responseId, response);
    }

    @Затем("Выполнение DELETE запроса на удаление пользователя \"(.+)\" через выполнение запроса api-клиентом \"(.+)\" на эндпоинт \"(.+)\" и получение ответа \"(.+)\" от сервера")
    public void deleteAnotherUser(String userForDeleteStashId, String apiClientNameId, String userEndpointId, String responseId) {
        User user = Context.getStash().get(userForDeleteStashId, User.class);

        RestRequest request = generatingRequest(
                new RestAssuredRequest(RestMethod.DELETE, String.format(userEndpointId, user.getId()), null, null, null)
        );

        RestApiClient apiClient = Context.getStash().get(apiClientNameId, RestApiClient.class);

        RestResponse response = apiClient.execute(request);

        Context.getStash().put(responseId, response);
    }

    @Затем("Проветка, что пользователь \"(.+)\" есть в базе данных")
    public void isUserInDataBase(String userStashId) {
        User user = Context.getStash().get(userStashId, User.class);

        User userFromDb = new UserRequests().read(user.getId());

        AllureAssert.assertNotNull(userFromDb);
    }

    @Затем("Проверка, что статус код из ответа \"(.+)\" = \"(.+)\"")
    public void assertStatusCode(String responseId, Integer statusCodeId) {

        RestResponse response = Context.getStash().get(responseId, RestResponse.class);

        AllureAssert.assertEquals(
                response.getStatusCode(),
                statusCodeId
        );
    }

    @Затем("Проверка отсутствия у пользователя \"(.+)\" прав администратора")
    public void isUserAdmin(String userStashId) {
        User user = Context.getStash().get(userStashId, User.class);

        assertFalse(user.getIsAdmin());
    }

    @Затем("Проверка формата Api-ключа у пользователя \"(.+)\" шестнадцатеричный формат \"(.+)\"")
    public void validationApiKey(String userStashId, String format) {
        User user = Context.getStash().get(userStashId, User.class);

        String apiKey = user.getTokens().get(0).getValue();

        assertTrue(apiKey.matches(format));
    }

    @Затем("Проверка, что тело ответа \"(.+)\" содержит данные пользователя из ответа \"(.+)\", в том числе его id")
    public void validateAnswerBody(String answerBody, String userStashId){

        RestResponse response = Context.getStash().get(answerBody, RestResponse.class);

        User user = getUserFromDbWithHisEmail(response.getPayload(UserInfoDto.class).getUser().getId());

        UserDto userFromResponse = initDtoFromResponse(response.getPayload(UserInfoDto.class).getUser());

        assertTrue(userFromResponse.getId() > 0);
        assertEquals(
                userFromResponse.getLogin(),
                user.getLogin()
        );
        assertEquals(
                userFromResponse.getIsAdmin(),
                Boolean.FALSE
        );
        assertEquals(
                userFromResponse.getFirstName(),
                user.getFirstName()
        );
        assertEquals(
                userFromResponse.getLastName(),
                user.getLastName()
        );
        assertEquals(
                userFromResponse.getMail(),
                user.getEmails().get(0).getAddress()
        );
        assertNotNull(
                userFromResponse.getCreatedOn(),
                "Дата создания существует и не null"
        );
        assertNull(userFromResponse.getLastLoginOn(),
                "Дата последнего логина не существует и равна null"
        );
        assertTrue(
                userFromResponse.getApiKey().matches("^[0-9a-fA-F]*$"),
                "Api-ключ имеет шестнадцатеричный формат"
        );

        Context.getStash().put(userStashId , user);
    }

    @Затем("Проверка, что в базе данных есть информация о созданном пользователе \"(.+)\", status = \"(.+)\"")
    public void validationUserStatus(String userStashId, Integer statusCode) {

        User user = Context.getStash().get(userStashId, User.class);

        assertEquals(
                user.getStatus().statusCode,
                statusCode,
                "Статус пользователя равен: " +statusCode
        );
    }

    private UserInfoDto getUserInfoDto(User user) {
        return initDto(
                new UserInfoDto(
                        new UserDto()
                                .setLogin(user.getLogin())
                                .setLastName(user.getLastName())
                                .setFirstName(user.getFirstName())
                                .setMail(user.getEmails().get(0).getAddress())
                                .setPassword(StringUtils.randomEnglishString(8))
                                .setStatus(user.getStatus().statusCode)
                )
        );
    }

    private User getUserFromDbWithHisEmail(Integer id) {
        User user = new UserRequests().read(id);

        Email email = new EmailRequests().readByUserId(user.getId());

        return user.setEmails(Collections.singletonList(email));
    }
}