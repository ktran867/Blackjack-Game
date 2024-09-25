package tests;

import org.junit.Test;
import static org.junit.Assert.*;

import gameComponents.Card;

public class CardTests {

    /**
     * This test method is for getting the rank of a card
     */
    @Test
    public void getRankTest() {
        Card card = new Card(5, "hearts");
        assertEquals(5, card.getRank());  
    }
    
    /**
     * This test method is for getting the suit of a card
     */
    @Test 
    public void getSuitTest() {
        Card card2 = new Card(5, "hearts");
        assertEquals("hearts", card2.getSuit());
    }
}
