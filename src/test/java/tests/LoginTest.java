package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import user.User;
import user.UserFactory;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {

    @Test
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

    @Test(dataProvider = "loginData")
    public void incorrectTests(User user, String errorMessage) {
        System.out.println("Incorrect Tests are running in thread: " + Thread.currentThread().getId());
        loginPage.open();
        loginPage.login(user);
        assertEquals(loginPage.checkErrorMsg(), errorMessage);
    }
}
