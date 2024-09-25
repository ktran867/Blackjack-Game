package tests;

import static org.junit.Assert.assertFalse;


import org.junit.Test;
import gameComponents.CardDeck;

public class CardDeckTest {
    
    /**
     * This test method is used to see if the newly created CardDeck object is empty
     * Because this CardDeck object had just been created it should have 52 cards and therefore past the test
     */
    @Test
    public void isEmptyTest() {
        CardDeck deck = new CardDeck();
        assertFalse(deck.isEmpty());
    }
}
