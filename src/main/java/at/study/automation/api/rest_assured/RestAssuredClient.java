package at.study.automation.api.rest_assured;

import at.study.automation.api.client.RestApiClient;
import at.study.automation.api.client.RestMethod;
import at.study.automation.api.client.RestRequest;
import at.study.automation.api.client.RestResponse;
import at.study.automation.model.user.Token;
import at.study.automation.model.user.User;
import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static at.study.automation.property.Property.getStringProperty;
import static io.restassured.RestAssured.given;

public class RestAssuredClient implements RestApiClient {

    protected RequestSpecification specification;

    public  RestAssuredClient() {
        this.specification = given()
                .baseUri(getStringProperty("url"))
                .contentType(ContentType.JSON);
    }

    public RestAssuredClient(User user) {
        this();
        String token = user.getTokens().stream()
                .filter(tkn -> tkn.getAction() == Token.TokenType.API)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("У пользователя нет API-токена"))
                .getValue();

        specification.header("X-Redmine-API-Key", token);
    }

    @Override
    @Step("Выполнение API-запроса")
    public RestResponse execute(RestRequest request) {
        RequestSpecification spec = given(specification)
                .queryParams(request.getQueryParameters())
                .headers(request.getHeaders())
                .filter(new AllureRestAssured());
        if (request.getBody() != null) {
            spec.body(request.getBody());
        }
        spec.log().all();

        Response response = spec.request(
                toRestAssuredMethod(request.getMethod()),
                request.getUri()
        );

        response.then().log().all();

        return new RestAssuredResponse(response);
    }

    private Method toRestAssuredMethod(RestMethod method) {
        return Method.valueOf(method.name());
    }
}
