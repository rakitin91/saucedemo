package tests;

import io.qameta.allure.*;
import org.testng.annotations.Test;
import user.UserFactory;

import static org.testng.Assert.*;

public class ProductsTest extends BaseTest {

    @Epic("Модуль товаров интернет-магазина")
    @Feature("Работа с корзиной")
    @Story("Tutorial_4")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Rakitin Roman")
    @TmsLink("saucedemo")
    @Test(description = "Проверка добавления товаров в корзину")
    @Description("Проверка добавления двух товаров в корзину и проверка их отображения")
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
