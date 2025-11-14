package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver browser;

    private static final By USERNAME = By.id("user-name");
    private static final By PASSWORD = By.id("password");
    private static final By BUTTON_LOGIN = By.id("login-button");
    private static final By PRODUCTS = By.xpath("//span[text()='Products']");
    private static final By ERRORMSG = By.cssSelector("h3[data-test='error']");

    public LoginPage(WebDriver browser) {
        this.browser = browser;
    }

    public void open() {
        browser.get("https://www.saucedemo.com/");
    }

    public void login(String username, String password) {
        browser.findElement(USERNAME).sendKeys(username);
        browser.findElement(PASSWORD).sendKeys(password);
        browser.findElement(BUTTON_LOGIN).click();
    }

    public String searchElement() {
        return browser.findElement(PRODUCTS).getText();
    }

    public String checkErrorMsg() {
        return browser.findElement(ERRORMSG).getText();
    }
}
