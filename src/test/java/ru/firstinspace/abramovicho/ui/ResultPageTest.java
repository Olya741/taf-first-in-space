package ru.firstinspace.abramovicho.ui;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.firstinspace.abramovicho.Util;

public class ResultPageTest extends BaseTest {
    String textForSearch = "рубашка";

    @Test
    @DisplayName("Test search with non-existent product name")
    public void testSearchWithEmptyResultList() {
        HomePage homePage = new HomePage();
        NavigationMenu menu = new NavigationMenu();
        homePage.closeSubscriptionDialog();
        String wrongText = RandomStringUtils.randomAlphabetic(10);
        menu.inputTextInSearchField(wrongText);
        ResultsPage resultsPage = new ResultsPage();
        Assertions.assertTrue(Util.findMatchInText(resultsPage.getEmptySearchResultMessage(), wrongText));
    }

    @Test
    @DisplayName("Test that the model from the search results contains text fragment")
    public void testSearchResultWithExistingName() {
        HomePage homePage = new HomePage();
        NavigationMenu menu = new NavigationMenu();
        homePage.closeSubscriptionDialog();
        menu.inputTextInSearchField(textForSearch);
        ResultsPage resultsPage = new ResultsPage();
        ModelInfoPage modelInfoPage = resultsPage.openModelDescription();
        Assertions.assertTrue(Util.findMatchInText(modelInfoPage.getDescription(), textForSearch));
    }
}
