package stellar.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import stellar.utils.Config;

public class LoginPage extends BasePage {

    // Локаторы
    private final By emailField = By.xpath("//input[@name='name']");
    private final By passwordField = By.xpath("//input[@name='Пароль']");
    private final By loginButton = By.xpath("//button[text()='Войти']");
    private final By registerLink = By.xpath("//a[text()='Зарегистрироваться']");
    private final By forgotPasswordLink = By.xpath("//a[text()='Восстановить пароль']");
    private final By loginHeader = By.xpath("//h2[text()='Вход']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть страницу входа")
    public void open() {
        driver.get(Config.LOGIN_PAGE);
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeader));
    }

    @Step("Войти с email: {email} и паролем")
    public void login(String email, String password) {
        driver.findElement(emailField).clear();
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(passwordField).clear();
        driver.findElement(passwordField).sendKeys(password);
        driver.findElement(loginButton).click();
    }

    @Step("Перейти на страницу регистрации")
    public void goToRegistration() {
        driver.findElement(registerLink).click();
        wait.until(ExpectedConditions.urlContains("/register"));
    }

    @Step("Перейти на страницу восстановления пароля")
    public void goToForgotPassword() {
        driver.findElement(forgotPasswordLink).click();
        wait.until(ExpectedConditions.urlContains("/forgot-password"));
    }

    @Step("Проверить, что страница входа загружена")
    public boolean isPageLoaded() {
        wait.until(ExpectedConditions.urlContains("/login"));
        return driver.findElement(loginHeader).isDisplayed();
    }

    @Step("Нажать ссылку 'Войти'")
    public void clickLoginLink() {
        By loginLink = By.xpath("//a[text()='Войти']");
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }
}