package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {

    @Test
    public void authorization() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitleText(), "Products", "Ожидался раздел 'Products'");
    }

    @Test
    public void lockedOutUser() {
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");
        assertEquals(
                loginPage.checkErrorMsg(),
                "Epic sadface: Sorry, this user has been locked out.",
                "Ожидался текст: 'Epic sadface: Sorry, this user has been locked out.'");
    }

    @Test(description = "Проверка авторизации без ввода пароля")
    public void noPassword() {
        loginPage.open();
        loginPage.fillInLogin("standard_user");
        loginPage.pressbtnLogin();
        assertEquals(
                loginPage.checkErrorMsg(), "Epic sadface: Password is required");
    }

    @Test(description = "Проверка авторизации без ввода логина")
    public void noLogin() {
        loginPage.open();
        loginPage.fillPassword("secret_sauce");
        loginPage.pressbtnLogin();
        assertEquals(
                loginPage.checkErrorMsg(), "Epic sadface: Username is required");
    }

    @Test(description = "Проверка авторизации, ввод невалидного пароля")
    public void invalidPassword() {
        loginPage.open();
        loginPage.fillInLogin("standard_user");
        loginPage.fillPassword("123");
        loginPage.pressbtnLogin();
        assertEquals(
                loginPage.checkErrorMsg(), "Epic sadface: Username and password do not match any user in this service");
    }
}