package stellar.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import stellar.api.UserClient;
import stellar.data.UserGenerator;
import stellar.model.User;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Тест проверяет успешную регистрацию пользователя с валидными данными. " +
            "Проверяет создание пользователя через UI и последующую верификацию через API.")
    public void successfulRegistrationTest() {
        // Генерируем данные для нового пользователя
        User newUser = UserGenerator.createRandomUser();

        // Открываем страницу регистрации
        registrationPage.open();
        assertTrue("Страница регистрации не загрузилась", registrationPage.isPageLoaded());

        // Регистрируем через UI
        registrationPage.register(
                newUser.getName(),
                newUser.getEmail(),
                newUser.getPassword()
        );

        // Ждем перехода на страницу входа
        registrationPage.waitForRedirectToLogin();
        assertTrue("После регистрации должны перейти на страницу входа",
                driver.getCurrentUrl().contains("/login"));

        // Проверяем что пользователь действительно создан
        var loginResponse = UserClient.loginUser(newUser);
        assertEquals("Пользователь не был создан в системе",
                200, loginResponse.getStatusCode());

        // Удаляем созданного пользователя
        if (loginResponse.getStatusCode() == 200) {
            String token = UserClient.extractToken(loginResponse);
            UserClient.deleteUser(token);
        }
    }

    @Test
    @DisplayName("Ошибка для некорректного пароля")
    @Description("Тест проверяет отображение ошибки при регистрации с паролем короче 6 символов. " +
            "Проверяет валидацию минимальной длины пароля на стороне фронтенда.")
    public void registrationWithShortPasswordTest() {
        User user = UserGenerator.createUserWithShortPassword();

        registrationPage.open();
        assertTrue("Страница регистрации не загрузилась", registrationPage.isPageLoaded());

        registrationPage.register(user.getName(), user.getEmail(), user.getPassword());

        assertTrue("Ошибка о коротком пароле не отображается",
                registrationPage.isPasswordErrorDisplayed());
    }
}