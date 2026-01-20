package stellar.tests;

import io.qameta.allure.Allure;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import stellar.api.UserClient;
import stellar.data.UserGenerator;
import stellar.model.User;
import stellar.pages.LoginPage;
import stellar.pages.MainPage;
import stellar.pages.RegistrationPage;
import stellar.utils.Config;
import stellar.utils.DriverManager;

public abstract class BaseTest {

    protected WebDriver driver;
    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected RegistrationPage registrationPage;

    protected User testUser;
    protected String userToken;

    @Before
    public void setUp() {
        // Настройка RestAssured
        RestAssured.baseURI = Config.API_URL;

        // Получаем браузер из системной переменной
        String browser = System.getProperty("browser", "chrome");

        System.out.println("Starting tests in browser: " + browser);

        // Добавляем информацию о браузере в Allure
        Allure.label("browser", browser);

        // Получаем драйвер
        driver = DriverManager.getDriver();

        // Инициализация Page Objects
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registrationPage = new RegistrationPage(driver);

        // Создание тестового пользователя через API
        prepareTestUser();
    }

    @After
    public void tearDown() {
        // Удаление тестового пользователя
        cleanupTestUser();

        // Закрытие браузера
        if (driver != null) {
            driver.quit();
        }
    }

    protected void prepareTestUser() {
        testUser = UserGenerator.createValidUser();

        var response = UserClient.registerUser(testUser);

        if (response.getStatusCode() == 200) {
            userToken = UserClient.extractToken(response);
            testUser.setAccessToken(userToken);
        } else {
            // Если пользователь уже существует, создаем нового
            testUser = UserGenerator.createRandomUser();
            var newResponse = UserClient.registerUser(testUser);
            userToken = UserClient.extractToken(newResponse);
            testUser.setAccessToken(userToken);
        }
    }

    protected void cleanupTestUser() {
        if (userToken != null && testUser != null) {
            UserClient.deleteUser(userToken);
        }
    }
}