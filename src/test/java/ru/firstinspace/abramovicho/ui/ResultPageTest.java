package ru.firstinspace.abramovicho.ui;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.firstinspace.abramovicho.utils.Util;

public class ResultPageTest extends BaseTest {
    String wrongText = RandomStringUtils.randomAlphabetic(10);
    String textForSearch = "рубашка";

    @Test
    @DisplayName("Test search with non-existent product name")
    public void testSearchWithEmptyResultList() {
        NavigationMenu menu = new NavigationMenu();
        menu.inputTextInSearchField(wrongText);
        ResultsPage resultsPage = new ResultsPage();
        Assertions.assertTrue(Util.doesTextContainsFragment(resultsPage.getEmptySearchResultMessage(), wrongText));
    }

    @Test
    @DisplayName("Test that the model from the search results contains text fragment")
    public void testSearchResultWithExistingName() {
        NavigationMenu menu = new NavigationMenu();
        menu.inputTextInSearchField(textForSearch);
        ResultsPage resultsPage = new ResultsPage();
        ModelInfoPage modelInfoPage = resultsPage.openModelDescription();
        Assertions.assertTrue(Util.doesTextContainsFragment(modelInfoPage.getDescription(), textForSearch));
    }
}
