package tests;

import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ProductsTest extends BaseTest {
    @Test
    public void checkGoodsAdded() {
        final String goodsName = "Test.allTheThings() T-Shirt (Red)";

        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");
        productsPage.isPageOpen();
        productsPage.addToCart(0);
        productsPage.addToCart(goodsName);
        productsPage.switchToCart();
        assertTrue(cartPage.getProductsNames().contains(goodsName));
        assertEquals(cartPage.getProductsNames().size(), 2);
        assertFalse(cartPage.getProductsNames().isEmpty());
    }
}
