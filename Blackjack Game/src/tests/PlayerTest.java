package tests;

import saveableObjects.Player;



import org.junit.Assert;
import org.junit.Test;

public class PlayerTest {

    /**
     * This test method is to check if the formatting is aligned with the format class
     */
    @Test
    public void playersTest() {
        Player player = new Player("kenny", 100, 0);
        String expected = String.format("*** %-30s --- %-25s ***", "Welcome Back! " + "kenny", "Your balance is $" + 100);
        Assert.assertEquals(expected, player.format(true));
    
        Player p2 = new Player("Khosro",200, 0);
        expected = String.format("*** %-30s --- %-25s ***", "Welcome Back! " + "Khosro", "Your balance is $" + 200);
        Assert.assertEquals(expected, p2.format(true));
    }
}