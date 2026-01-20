package stellar.utils;

public class Config {
    // Base URLs
    public static final String BASE_URL = "https://stellarburgers.education-services.ru";
    public static final String API_URL = BASE_URL + "/api";

    // API endpoints
    public static final String REGISTER_ENDPOINT = "/auth/register";
    public static final String LOGIN_ENDPOINT = "/auth/login";
    public static final String USER_ENDPOINT = "/auth/user";

    // Pages
    public static final String LOGIN_PAGE = BASE_URL + "/login";
    public static final String REGISTER_PAGE = BASE_URL + "/register";
    public static final String FORGOT_PASSWORD_PAGE = BASE_URL + "/forgot-password";
    public static final String MAIN_PAGE = BASE_URL + "/";

    // Browser settings
    public static final String YANDEX_BROWSER_PATH =
            "C:\\Users\\andre\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe";

    // Timeouts
    public static final int DEFAULT_TIMEOUT_SECONDS = 10;
}