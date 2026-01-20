package stellar.tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LoginTest extends BaseTest {

    @Test
    @DisplayName("Вход по кнопке «Войти в аккаунт» на главной")
    @Description("Тест проверяет вход через кнопку «Войти в аккаунт» на главной странице. " +
            "Проверяет возможность аутентификации пользователя с валидными данными.")
    public void loginViaMainPageButtonTest() {
        mainPage.open();
        assertTrue("Главная страница не загрузилась", mainPage.isPageLoaded());

        mainPage.clickLoginAccountButton();
        assertTrue("Страница входа не загрузилась", loginPage.isPageLoaded());

        loginPage.login(testUser.getEmail(), testUser.getPassword());

        mainPage.waitForMainPageAfterLogin();
        assertTrue("Вход не выполнен", mainPage.isPageLoaded());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Тест проверяет вход через кнопку «Личный кабинет» в хедере. " +
            "Проверяет альтернативный способ аутентификации пользователя.")
    public void loginViaPersonalAccountButtonTest() {
        mainPage.open();
        assertTrue("Главная страница не загрузилась", mainPage.isPageLoaded());

        mainPage.clickPersonalAccountButton();
        assertTrue("Страница входа не загрузилась", loginPage.isPageLoaded());

        loginPage.login(testUser.getEmail(), testUser.getPassword());

        mainPage.waitForMainPageAfterLogin();
        assertTrue("Вход не выполнен", mainPage.isPageLoaded());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Тест проверяет вход через ссылку «Войти» на странице регистрации. " +
            "Проверяет переход со страницы регистрации на страницу входа.")
    public void loginViaRegistrationFormLinkTest() {
        registrationPage.open();
        assertTrue("Страница регистрации не загрузилась", registrationPage.isPageLoaded());

        registrationPage.goToLogin();
        assertTrue("Страница входа не загрузилась", loginPage.isPageLoaded());

        loginPage.login(testUser.getEmail(), testUser.getPassword());

        mainPage.waitForMainPageAfterLogin();
        assertTrue("Вход не выполнен", mainPage.isPageLoaded());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Тест проверяет вход через ссылку «Войти» на странице восстановления пароля. " +
            "Проверяет переход со страницы восстановления пароля на страницу входа.")
    public void loginViaPasswordRecoveryFormLinkTest() {
        loginPage.open();
        loginPage.goToForgotPassword();
        loginPage.clickLoginLink();

        assertTrue("Страница входа не загрузилась", loginPage.isPageLoaded());

        loginPage.login(testUser.getEmail(), testUser.getPassword());

        mainPage.waitForMainPageAfterLogin();
        assertTrue("Вход не выполнен", mainPage.isPageLoaded());
    }
}