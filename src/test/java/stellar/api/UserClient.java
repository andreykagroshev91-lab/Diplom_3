package stellar.api;

import stellar.model.User;
import stellar.utils.Config;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class UserClient {

    static {
        RestAssured.baseURI = Config.API_URL;
    }

    public static Response registerUser(User user) {
        return given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(Config.REGISTER_ENDPOINT);
    }

    public static Response loginUser(User user) {
        return given()
                .header("Content-Type", "application/json")
                .body(user)
                .when()
                .post(Config.LOGIN_ENDPOINT);
    }

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

    public static String extractToken(Response response) {
        return response.then().extract().path("accessToken");
    }
}