package ru.firstinspace.abramovicho.ui;

import org.junit.jupiter.api.Test;
import ru.firstinspace.abramovicho.enums.Menu;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CartTest extends BaseTest {

    @Test
    public void testAddProductToCart() {
        NavigationMenu menu = new NavigationMenu();
        menu.openSection(Menu.SALE);
        ResultsPage resultsPage = new ResultsPage();
        String modelName = resultsPage.getModelName();
        String modelSize = resultsPage.getAvailableSize();
        resultsPage.addProductToCart();
        CartModal cartModal = new CartModal();

        assertAll(
                () -> assertEquals(modelName, cartModal.getProductName()),
                () -> assertEquals(modelSize, cartModal.getProductSize())
        );
    }
}
