package userInterface;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


import saveableObjects.Player;
import gameComponents.Card;

public class Menu {

	/**
	 * This class will be used to show the menus and sub menus to the user
	 * It also prompts the user for the inputs and validates them 
	 */

	Scanner userInput;	// Initialize Scanner object input

	/**
	 * Constructor for the Menu
	 */
	public Menu() {
		userInput = new Scanner(System.in);
	}

	/**
	 * Displays the main menu screen and prompts the user for input
	 * @return - the users choice
	 * @throws FileNotFoundException
	 */
	public char mainMenuScreen() throws FileNotFoundException {
		System.out.println("Select one of the following options: \n");
		System.out.println("\t(P) Play game");
		System.out.println("\t(S) Search");
		System.out.println("\t(E) Exit game\n");
		System.out.print("Enter a choice: ");

		char option = userInput.nextLine().toLowerCase().charAt(0); //The "nextline" method will read the whole line, 
																	//but it isn't necessary to read the whole line therefore using the charAt method will return only the first character
		return option;
}

	/**
	 * This method displays the sub menu screen and prompts the user for input
	 * @return - The users choice
	 * @throws FileNotFoundException
	 */
	public char subMenuScreen() throws FileNotFoundException {
		System.out.println("Select one of these options: \n");
		System.out.println("\t(T) Top player (Most number of wins)");
		System.out.println("\t(N) Looking for a Name");
		System.out.println("\t(B) Back to main menu\n");
		System.out.print("Enter a choice: ");

		char option = userInput.nextLine().toLowerCase().charAt(0);	//The "nextline" method will read the whole line, 
																	//but it isn't necessary to read the whole line therefore using the charAt method will return only the first character
		return option;
}

	/**
	 * This method is used to ask for the users name
	 * @return the name provided by user
	 */
	public String enterName() {
		System.out.print("What is your name: ");
		String name = userInput.nextLine();

		return name;
	}
	/**
	 * A method that print stars for formatting user interface 
	 * @param number - 		the number of stars to print
	 * @param character - 	the chracter representing a star
	 */
	public void printStars(int number, String character) {
		for( int i = 0; i < number; i++) {
			System.out.print(character);
		}		
	}

	/**
	 * This method prints a separator consisting of a given character repeated a given number of times between '+' characters. The number of columns is 
	 * also specified as a parameter.
	 * @param number - 		The number of times to repeat the character
	 * @param character - 	The character used for the separator 
	 * @param columns -		The number of columns in the separator
	 */
	public void printSeparator(int number ,String character, int columns) {
		System.out.print("+");
		for(int i = 0; i < columns; i++) {
			printStars(number, character);
			System.out.print("+");
		}
		System.out.println();
	}

	/**
	 * This method prints out the table when the user select "T" for top players, taking an ArrayList of players for the input
	 * @param players - The Player object that will be used to go through the for loop to sort through top players 
	 */
	public void topPlayer(ArrayList<Player> players) {
		System.out.println("\n");
		printStars(28, " ");
		System.out.println("-- TOP PLAYERS -- \n");
		printSeparator(35, "=", 2);
		System.out.println(String.format("|%-35s|%-35s|","NAME", "# WINS"));
		printSeparator(35, "=", 2);
		for (Player player : players) {
			System.out.println(String.format("|%-35s|%-35s|",player.getName(), player.getWins()));
			printSeparator(35, "=", 2);
		}
		System.out.println();	//used to skip line
	}

	/**
	 * This method is used to display the players name, balance and wins in a table format.
	 * The table is created using the printStars and printSeparators method to print a line of stars or a line of plus signs that will separate the rows and columns of the table
	 * @param player - The player object that will print out the player the user is searching for
	 */
	public void searchPlayers(Player player) {
		System.out.println("\n");
		printStars(20, " ");
		System.out.println("-- PLAYER INFO --\n");
		printSeparator(28, "=", 3);
		System.out.println(String.format("|%-28s|%-28s|%-28s|", "NAME", "BALANCE", "# WINS"));
		printSeparator(28, "=", 3);
		System.out.println(String.format("|%-28s|%-28s|%-28s|", player.getName(), player.getBalance(), player.getWins()));
		printSeparator(28, "=", 3);
	}

	/**
	 * This method is used to display the players deatails 
	 * @param user - The player object we want to display
	 * @param existing - Used to determine if the player exists
	 */
	public void showPlayer(Player user, boolean existing) {
		printStars(70, "*");
		System.out.println();
		System.out.println(user.format(existing));
		printStars(70, "*");
		System.out.println();
	}

	/**
	 * This method will ask the user how much they would like to bet
	 * @return an input depending on user
	 */
	public int getBetAmount() {
		System.out.println("How much do you want to bet this round? ");
		return userInput.nextInt();
	}

	/**
	 * This method will display the game title 
	 */
	public void gameTitle() {
		System.out.println();	//used to skip a line 
		printStars(28, " ");
		System.out.println("- BLACKJACK -");
	}

	/**
	 * This method will display the hand of both the player and dealer
	 * @param playersHand - ArrayList of cards representing hand of player 
	 * @param dealersHand - ArrayList of cards representing hand of dealer
	 * @param hideSecondCard - A boolean flag indicating whther to hide the second card of the dealer
	 */
	public void displayHand(ArrayList<Card> playersHand, ArrayList<Card> dealersHand, boolean hideSecondCard) {
		gameTitle();
		printSeparator(35, "=", 2);
		System.out.println(String.format("|%-35s|%-35s|","PLAYER", "DEALER" ));		//The header for the player's and dealer's hand
		printSeparator(35, "=", 2);

		int maxCards =  Math.max(playersHand.size(), dealersHand.size());	//Determine the maxmimum number of cards between the player's and dealer's hand

		//Iterates through the cards to display them
		for (int i = 0; i < maxCards; i++) {
			String playerCard = (i < playersHand.size()) ? playersHand.get(i).toString() : "";		//Get a string representation of the dealer's card, or an empty string if there are no more cards
			String dealerCard;
			if (i == 1 && hideSecondCard) {
				dealerCard = "";
			}
			else {
				dealerCard = (i < dealersHand.size()) ? dealersHand.get(i).toString() : "";
			}
	
			//Adjust the players and dealers card to the left column
			String playerColumn = String.format("%-34s|", playerCard);  // Player's hand aligned to the left
			String dealerColumn = String.format("-%-34s|", dealerCard);  // Dealer's hand aligned to the left


			System.out.println(String.format("|-%35s|-%35s|", playerColumn, dealerColumn));		//Print the formatted strings for both player's and dealer's cards
			printSeparator(35, "-", 2);
		}
		} 
	
	/**
	 * This method will display if the player has insufficient funds
	 */
	public void insufficientFunds() {
		System.out.println("You have an insufficient balance");
	}

	/**
	 * This method will ask the user if they want to play again if user input "y" the game will start again, if the user inputs "n" then the game will terminate
	 * @return
	 */
	public boolean playAgain() {
		while (true) {
			System.out.print("Do you want to continue (y/n)? ");
			char choice = userInput.nextLine().toLowerCase().charAt(0);
			if (choice == 'y') {
				return true;
			}
			else if (choice == 'n') {
				return false;
			}
			else {
				System.out.println("Invalid option");
			}
			}
		}

	/**
	 * This method tells the user to press enter to go back
	 */
	public void back() {
		System.out.println("\nPress \"enter\" to go back");
		userInput.nextLine();
	}

	/**
	 * This method is the result of the user pressing "e" to exit game
	 */
	public void saveAndExit() {
		System.out.println("\nSaving...");
		System.out.println("Done! Please visit us again!");
	}

	/**
	 * This method displays if the player balance is too low
	 */
	public void lowBalanceError() {
		System.out.println("The player's balance is too low");
	}

	/**
	 * This method is used if the player is not found when searching for a name
	 */
	public void playerNotFound() {
		System.out.println("Player was not found, you will be sent back to the main menu");
	}
}

