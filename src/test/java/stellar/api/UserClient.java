package stellar.api;

import stellar.model.User;
import stellar.utils.Config;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.qameta.allure.Step;
import static io.restassured.RestAssured.given;

public class UserClient {

    static {
        RestAssured.baseURI = Config.API_URL;
    }

    @Step("Регистрация пользователя")
    public static Response registerUser(User user) {
        return given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(Config.REGISTER_ENDPOINT);
    }

    @Step("Авторизация пользователя")
    public static Response loginUser(User user) {
        return given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(Config.LOGIN_ENDPOINT);
    }

    @Step("Удаление пользователя")
    public static void deleteUser(String accessToken) {
        if (accessToken != null && !accessToken.isEmpty()) {
            given()
                    .header("Authorization", accessToken)
                    .when()
                    .delete(Config.USER_ENDPOINT)
                    .then()
                    .statusCode(202);
        }
    }

    @Step("Извлечение токена из ответа")
    public static String extractToken(Response response) {
        return response.then().extract().path("accessToken");
    }
}