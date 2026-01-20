package stellar.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {

    public static WebDriver getDriver() {
        String browser = System.getProperty("browser", "chrome").toLowerCase();

        System.out.println("Using browser: " + browser);

        WebDriver driver;
        switch (browser) {
            case "yandex":
                driver = startYandexBrowser();
                break;
            case "chrome":
            default:
                driver = startChrome();
        }

        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver startYandexBrowser() {
        // Драйвер для Яндекс 142
        String driverPath = "F:/ProjectJavaQA/MavenProjects/Diplom/Diplom_3_1/drivers/yandexdriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);
        System.out.println("Using driver: " + driverPath);

        ChromeOptions options = new ChromeOptions();
        options.setBinary(Config.YANDEX_BROWSER_PATH);
        options.addArguments(
                "--incognito",
                "--start-maximized",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--remote-allow-origins=*"
        );

        options.setExperimentalOption("excludeSwitches",
                new String[]{"enable-automation"});

        return new ChromeDriver(options);
    }

    private static WebDriver startChrome() {
        // Драйвер для Chrome 143
        String driverPath = "F:/ProjectJavaQA/MavenProjects/Diplom/Diplom_3_1/drivers/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver", driverPath);
        System.out.println("Using driver: " + driverPath);

        ChromeOptions options = new ChromeOptions();
        options.addArguments(
                "--incognito",
                "--start-maximized",
                "--no-sandbox",
                "--disable-dev-shm-usage",
                "--remote-allow-origins=*"
        );

        options.setExperimentalOption("excludeSwitches",
                new String[]{"enable-automation"});

        return new ChromeDriver(options);
    }
}