package stellar.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

    public static WebDriver getDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();
        System.out.println("Creating WebDriver for: " + browser);

        WebDriver driver;
        switch (browser) {
            case "yandex":
                driver = createYandexDriver();
                break;
            case "chrome":
            default:
                driver = createChromeDriver();
        }

        // Устанавливаем разрешение 1920x1080
        driver.manage().window().setSize(new Dimension(1920, 1080));
        System.out.println("Browser started in incognito mode with resolution 1920x1080");

        return driver;
    }

    private static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--incognito",           // Режим инкогнито
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--disable-blink-features=AutomationControlled"
        );

        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        return new ChromeDriver(options);
    }

    private static WebDriver createYandexDriver() {
        // Для Яндекс нужен ручной путь к драйверу
        String driverPath = "F:/ProjectJavaQA/MavenProjects/Diplom/Diplom_3_1/drivers/yandexdriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);

        ChromeOptions options = new ChromeOptions();
        options.setBinary(Config.YANDEX_BROWSER_PATH);

        options.addArguments(
                "--incognito",           // Режим инкогнито
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--disable-blink-features=AutomationControlled"
        );

        options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
        options.setExperimentalOption("useAutomationExtension", false);

        return new ChromeDriver(options);
    }
}