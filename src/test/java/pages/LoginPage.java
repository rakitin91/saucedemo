package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import user.User;

public class LoginPage extends BasePage {

    private By loginInput = By.id("user-name");
    private By passInput = By.id("password");
    private By btnLogin = By.id("login-button");
    private By errorMsg = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открытие url страницы")
    public void open() {
        driver.get(BASE_URL);
    }

    @Step("Логинимся под учетными данными пользователя")
    public void login(User user) {
        fillInLogin(user.getEmail());
        fillPassword(user.getPassword());
        pressbtnLogin();
    }

    @Step("Вводим логин = {username}")
    public void fillInLogin(String username) {
        driver.findElement(loginInput).sendKeys(username);
    }

    @Step("Вводим пароль = *****")
    public void fillPassword(String password) {
        driver.findElement(passInput).sendKeys(password);
    }

    @Step("Нажимаем кнопку 'Login'")
    public void pressbtnLogin() {
        driver.findElement(btnLogin).click();
    }

    @Step("Проверяем текст сообщения об ошибке")
    public String checkErrorMsg() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg));
        return driver.findElement(errorMsg).getText();
    }
}
