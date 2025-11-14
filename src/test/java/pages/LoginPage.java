package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class LoginPage extends BasePage {

    private By loginInput = By.id("user-name");
    private By passInput = By.id("password");
    private By btnLogin = By.id("login-button");
    private By errorMsg = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get(BASE_URL);
    }

    public void login(String username, String password) {
        fillInLogin(username);
        fillPassword(password);
        pressbtnLogin();
    }

    public void fillInLogin(String username) {
        driver.findElement(loginInput).sendKeys(username);
    }

    public void fillPassword(String password) {
        driver.findElement(passInput).sendKeys(password);
    }

    public void pressbtnLogin() {
        driver.findElement(btnLogin).click();
    }

    public String checkErrorMsg() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(errorMsg));
        return driver.findElement(errorMsg).getText();
    }
}