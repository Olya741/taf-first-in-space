package ru.firstinspace.abramovicho.ui;

import org.junit.jupiter.api.Test;
import ru.firstinspace.abramovicho.enums.Menu;

public class CartTest extends BaseTest {

    @Test
    public void test() {
        HomePage homePage = new HomePage();
        homePage.closeSubscriptionDialog();
        NavigationMenu menu = new NavigationMenu();
        menu.openSection(Menu.SALE);
        ResultsPage resultsPage = new ResultsPage();
        System.out.println(resultsPage.getModelName());
    }
}
