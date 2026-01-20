package stellar.tests;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import stellar.api.UserClient;
import stellar.data.UserGenerator;
import stellar.model.User;
import stellar.pages.LoginPage;
import stellar.pages.MainPage;
import stellar.pages.RegistrationPage;
import stellar.utils.Config;
import stellar.utils.DriverManager;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public abstract class BaseTest {

    protected WebDriver driver;
    protected MainPage mainPage;
    protected LoginPage loginPage;
    protected RegistrationPage registrationPage;

    protected User testUser;
    protected String userToken;

    protected final String browserType;

    public BaseTest(String browserType) {
        this.browserType = browserType;
    }

    @Parameterized.Parameters(name = "Browser: {0}")
    public static Collection<Object[]> getBrowserParams() {
        return Arrays.asList(new Object[][] {
                {"chrome"},
                {"yandex"}
        });
    }

    @Before
    public void setUp() {
        // Настройка RestAssured
        RestAssured.baseURI = Config.API_URL;

        // Устанавливаем системное свойство для браузера
        System.setProperty("browser", browserType);

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