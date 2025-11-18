package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class LoginTest extends BaseTest {

    @Test
    public void authorization() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(productsPage.getTitleText(), "Products", "Ожидался раздел 'Products'");
    }

    @DataProvider()
    public Object[][] loginData() {
        return new Object[][]{
                {"locked_out_user", "secret_sauce", "Epic sadface: Sorry, this user has been locked out."},
                {"standard_user", "", "Epic sadface: Password is required"},
                {"", "secret_sauce", "Epic sadface: Username is required"},
                {"standard_user", "123", "Epic sadface: Username and password do not match any user in this service"}
        };
    }

    @Test(dataProvider = "loginData")
    public void incorrectTests(String user, String password, String errorMessage) {
        loginPage.open();
        loginPage.login(user, password);
        assertEquals(loginPage.checkErrorMsg(), errorMessage);
    }
}
