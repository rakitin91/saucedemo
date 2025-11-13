package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FirstClass extends BaseTest {

    @Test
    public void authorization() {
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        assertEquals(loginPage.searchElement(), "Products", "Ожидался раздел 'Products'");

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
}

