package tests;

import io.qameta.allure.*;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;
import user.UserFactory;
import utils.AllureUtils;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {

    @Epic("Модуль логина интернет-магазина")
    @Feature("Авторизация")
    @Story("Tutorial_4")
    @Severity(SeverityLevel.BLOCKER)
    @Owner("Rakitin Roman")
    @TmsLink("saucedemo")
    @Issue("saucedemo")
    @Test(description = "Проверка авторизации")
    @Description("Проверка успешной авторизации с валидными данными")
    public void authorization() {
        System.out.println("Authorization Test are running in thread: " + Thread.currentThread().getId());
        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        assertEquals(productsPage.getTitleText(), "Products", "Ожидался раздел 'Products'");
    }

    @DataProvider()
    public Object[][] loginData() {
        return new Object[][]{
                {UserFactory.withLockedUserPermission(), "Epic sadface: Sorry, this user has been locked out."},
                {UserFactory.withEmptyPassword(), "Epic sadface: Password is required"},
                {UserFactory.withEmptyLogin(), "Epic sadface: Username is required"},
                {UserFactory.withInvalidPassword(), "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Epic("Модуль логина интернет-магазина")
    @Feature("Авторизация")
    @Story("Tutorial_4")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Rakitin Roman")
    @TmsLink("saucedemo")
    @Test(dataProvider = "loginData", description = "Проверка различных сценариев некорректной авторизации")
    @Description("Тестирование с невалидными учетными данными")
    public void incorrectTests(User user, String errorMessage) {
        System.out.println("Incorrect Tests are running in thread: " + Thread.currentThread().getId());
        loginPage.open();
        loginPage.login(user);
        assertEquals(loginPage.checkErrorMsg(), errorMessage);
    }
}
