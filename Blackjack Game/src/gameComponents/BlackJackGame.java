package gameComponents;


import java.util.ArrayList;
import java.util.Scanner;


import saveableObjects.Player;
import userInterface.Menu;


public class BlackJackGame {
    	/**
	 * In this class you implement the game
	 * You should use CardDeck class here
	 * See the instructions for the game rules
	 */

	/**
	 * @param deck -        The card deck
	 * @param dealersHand - The dealers hand
	 * @param playersHand - The players hand
     * @param menuApp -     The menu user interface
     * @param player -      The player
     * @param keyboard -    Scanner object for user input
	 */
	private CardDeck deck;                  
	private ArrayList<Card> dealersHand;    
	private ArrayList<Card> playersHand;    
    Menu menuApp;                           
    Player player;
	Scanner keyboard;
	
    /**
     * Constructor for the blackjack game
     * @param player - The player playing the game
     */
	public BlackJackGame(Player player) {
		this.deck = new CardDeck();             //Initialize the deck
        this.menuApp = new Menu();              //Initialize the menu
		this.dealersHand = new ArrayList<>();   //Initialize the dealer's hand
		this.playersHand = new ArrayList<>();   //Initialize the player's hand
        this.player = player;                   //Set the player
        this.keyboard = new Scanner(System.in); //Initialize Scanner for user input
	}

    /**
     * This method will begin the game 
     * @param name - The name of the player
     */
    public void game(String name) {
       int betAmount;
       while(true) {
        betAmount = menuApp.getBetAmount();         // Gets the users bet amount
        if (betAmount > player.getBalance()) {      // If the user bet amount is greater than the players balance, Display low balance message
            menuApp.lowBalanceError();              // Display low balance error
        }
        else {
            playRound(betAmount);   //Start a round of the game
            break;
        }
       }
    }

    /**
     * This method is used to play a round of the game
     * @param betAmount - The users bet amount
     */
    private void playRound(int betAmount) {
        dealersHand.clear();    // Clear dealer's hand
		playersHand.clear();    // Clear player's hand
        deck.reset();           // Reset the deck
        dealCards();            // Deal cardss to player and dealer

        menuApp.displayHand(playersHand, dealersHand, true);    //Display player's and dealer's hand

        playersTurn(betAmount); 

        if (calculateHandValue(playersHand) <= 21) {
            dealersTurn();
            calculateResults(betAmount); //Calculates the game results
        }
    }
    
    
	/**
     * This method is to deal cards initially to both player and dealer
     */
	public void dealCards(){
        playersHand.add(deck.dealCard());
		dealersHand.add(deck.dealCard());
		playersHand.add(deck.dealCard());
		dealersHand.add(deck.dealCard());
		
		
		
	}

	/**
     * This method is used to calculate the value of your cards
     * @param hand - The players hand
     * @return the value of the hand
     */
	private int calculateHandValue(ArrayList<Card> hand) {
        int value = 0;
        int aceCount = 0;

        for (Card card : hand) {
            if (card.getRank() == 1) {
                aceCount++;
                value += 11; 
            } else if (card.getRank() >= 10) {
                value += 10;
            } else {
                value += card.getRank();
            }
        }

        // Adjust for aces if needed
        while (value > 21 && aceCount > 0) {
            value -= 10;
            aceCount--;
        }

        return value;
    }

	/**
     * This method is used to get the players turn
     */
	public void playersTurn(int betAmount) {
        while (true) {

            System.out.print("Hit or Stand (h/s): ");
            String decision = keyboard.nextLine().toLowerCase();
            if (decision.equals("h")) {
                playersHand.add(deck.dealCard());
				menuApp.displayHand(playersHand, dealersHand, true);
                if (calculateHandValue(playersHand) > 21) {
                    System.out.println("You lose $" + betAmount);           //Displays that the player lost
                    player.setBalance(player.getBalance() - betAmount);     ////Subtract the players balance with the bet amount
                    return;
                }
            } else if (decision.equals("s")) {
                dealersTurn();
                return;
            } 
        }
    }
	/**
     * This method is used to get the dealers turn
     */
	public void dealersTurn(){
		// The dealer will continue hitting as long the total is less than 17
		while (calculateHandValue(dealersHand) < 17){
			dealersHand.add(deck.dealCard());  // Add a new card to the dealer's hand from the deck
	}
        // Display the updated hands of both players and the dealer, with second card hidden
        menuApp.displayHand(playersHand, dealersHand, false);
}

    /**
     * This method is used to calculate the game results and the amount the user will receive or lose.
     * @param betAmount - the amount betted by the player
     */
    private void calculateResults(int betAmount) {
        int playerScore = calculateHandValue(playersHand);
        int dealerScore = calculateHandValue(dealersHand);
        int winnings = betAmount * 2;

        if (dealerScore > 21 || playerScore > dealerScore) {
            System.out.println("You won $" + winnings + "!");       
            player.setBalance(player.getBalance() + winnings);      //Double the users bet amount and adds it to the balance
            player.setWins(player.getWins() + 1);                   //Adds a win to the users win column

        }else if (dealerScore > playerScore) {
            System.out.println("You lose $" + betAmount);           //Displays that the player lost
            player.setBalance(player.getBalance() - betAmount);     //Subtract the players balance with the bet amount
           
        }else {
        System.out.println("It's a tie!");
    }
    }

}
    



