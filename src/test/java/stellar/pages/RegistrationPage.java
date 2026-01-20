package stellar.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import stellar.utils.Config;

public class RegistrationPage extends BasePage {

    // Локаторы
    private final By nameField = By.xpath("//fieldset[1]//input");
    private final By emailField = By.xpath("//fieldset[2]//input");
    private final By passwordField = By.xpath("//fieldset[3]//input");
    private final By registerButton = By.xpath("//button[text()='Зарегистрироваться']");
    private final By loginLink = By.xpath("//a[text()='Войти']");
    private final By passwordError = By.xpath("//p[contains(text(), 'Некорректный пароль')]");
    private final By registerHeader = By.xpath("//h2[text()='Регистрация']");

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть страницу регистрации")
    public void open() {
        driver.get(Config.REGISTER_PAGE);
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerHeader));
    }

    @Step("Зарегистрироваться с именем: {name}, email: {email}, паролем")
    public void register(String name, String email, String password) {
        driver.findElement(nameField).clear();
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(registerButton).click();
    }

    @Step("Проверить отображение ошибки пароля")
    public boolean isPasswordErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError)).isDisplayed();
    }

    @Step("Перейти на страницу входа")
    public void goToLogin() {
        driver.findElement(loginLink).click();
        wait.until(ExpectedConditions.urlContains("/login"));
    }

    @Step("Проверить, что страница регистрации загружена")
    public boolean isPageLoaded() {
        wait.until(ExpectedConditions.urlContains("/register"));
        return driver.findElement(registerHeader).isDisplayed();
    }

    @Step("Ожидать перехода на страницу входа после регистрации")
    public void waitForRedirectToLogin() {
        wait.until(ExpectedConditions.urlToBe(Config.LOGIN_PAGE));
    }
}