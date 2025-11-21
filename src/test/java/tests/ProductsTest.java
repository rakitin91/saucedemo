package tests;

import org.testng.annotations.Test;
import user.UserFactory;

import static org.testng.Assert.*;

public class ProductsTest extends BaseTest {
    @Test
    public void checkGoodsAdded() {
        final String goodsName = "Test.allTheThings() T-Shirt (Red)";
        System.out.println("Products Test are running in thread: " + Thread.currentThread().getId());

        loginPage.open();
        loginPage.login(UserFactory.withAdminPermission());
        productsPage.isPageOpen();
        productsPage.addToCart(0);
        productsPage.addToCart(goodsName);
        productsPage.switchToCart();
        assertTrue(cartPage.getProductsNames().contains(goodsName));
        assertEquals(cartPage.getProductsNames().size(), 2);
        assertFalse(cartPage.getProductsNames().isEmpty());
    }
}
