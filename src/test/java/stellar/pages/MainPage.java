package stellar.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import stellar.utils.Config;

public class MainPage extends BasePage {

    private final By mainTitle = By.xpath("//h1[contains(text(), 'Соберите бургер')]");
    private final By loginAccountButton = By.xpath("//button[text()='Войти в аккаунт']");
    private final By personalAccountButton = By.xpath("//p[text()='Личный Кабинет']");

    // Локаторы вкладок
    private final By bunsTab = By.xpath("//span[text()='Булки']/parent::div[contains(@class, 'tab_tab__')]");
    private final By saucesTab = By.xpath("//span[text()='Соусы']/parent::div[contains(@class, 'tab_tab__')]");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']/parent::div[contains(@class, 'tab_tab__')]");

    // Локаторы заголовков разделов
    private final By bunsHeader = By.xpath("//h2[text()='Булки']");
    private final By saucesHeader = By.xpath("//h2[text()='Соусы']");
    private final By fillingsHeader = By.xpath("//h2[text()='Начинки']");

    // Класс активной вкладки - ДОБАВЛЯЕМ static
    private static final String ACTIVE_TAB_CLASS = "tab_tab_type_current__2BEPc";

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть главную страницу")
    public void open() {
        driver.get(Config.MAIN_PAGE);
        wait.until(ExpectedConditions.visibilityOfElementLocated(mainTitle));
    }

    @Step("Нажать кнопку 'Войти в аккаунт'")
    public void clickLoginAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginAccountButton)).click();
    }

    @Step("Нажать кнопку 'Личный кабинет'")
    public void clickPersonalAccountButton() {
        wait.until(ExpectedConditions.elementToBeClickable(personalAccountButton)).click();
    }

    @Step("Перейти в раздел 'Булки'")
    public void goToBunsSection() {
        clickTab(bunsTab);
        waitForTabActive(bunsTab);
    }

    @Step("Перейти в раздел 'Соусы'")
    public void goToSaucesSection() {
        clickTab(saucesTab);
        waitForTabActive(saucesTab);
    }

    @Step("Перейти в раздел 'Начинки'")
    public void goToFillingsSection() {
        clickTab(fillingsTab);
        waitForTabActive(fillingsTab);
    }

    @Step("Проверить, что раздел 'Булки' активен")
    public boolean isBunsSectionActive() {
        return isTabActive(bunsTab);
    }

    @Step("Проверить, что раздел 'Соусы' активен")
    public boolean isSaucesSectionActive() {
        return isTabActive(saucesTab);
    }

    @Step("Проверить, что раздел 'Начинки' активен")
    public boolean isFillingsSectionActive() {
        return isTabActive(fillingsTab);
    }

    @Step("Проверить, что заголовок раздела виден: {sectionName}")
    public boolean isSectionHeaderVisible(String sectionName) {
        try {
            switch (sectionName) {
                case "Булки":
                    return driver.findElement(bunsHeader).isDisplayed();
                case "Соусы":
                    return driver.findElement(saucesHeader).isDisplayed();
                case "Начинки":
                    return driver.findElement(fillingsHeader).isDisplayed();
                default:
                    return false;
            }
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Проверить, что главная страница загружена")
    public boolean isPageLoaded() {
        try {
            return driver.findElement(mainTitle).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    @Step("Ожидать загрузки главной страницы после входа")
    public void waitForMainPageAfterLogin() {
        wait.until(ExpectedConditions.urlToBe(Config.MAIN_PAGE));
        wait.until(ExpectedConditions.visibilityOfElementLocated(mainTitle));
    }

    // Убираем @Step из приватных методов
    private void clickTab(By tabLocator) {
        wait.until(ExpectedConditions.elementToBeClickable(tabLocator)).click();
    }

    private void waitForTabActive(By tabLocator) {
        wait.until(driver -> isTabActive(tabLocator));
    }

    private boolean isTabActive(By tabLocator) {
        try {
            String classAttribute = driver.findElement(tabLocator).getAttribute("class");
            return classAttribute != null && classAttribute.contains(ACTIVE_TAB_CLASS);
        } catch (Exception e) {
            return false;
        }
    }
}