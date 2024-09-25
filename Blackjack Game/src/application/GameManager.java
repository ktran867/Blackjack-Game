package application;

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;


import userInterface.Menu;
import saveableObjects.Player;
import gameComponents.BlackJackGame;


public class GameManager {
	
	/* This class handles the list of players, and handles the flow of information between the other classes
	 * It will probably need at least the following methods:
	 * 		A method to load the player data in the txt file into an arraylist of Player objects
	 * 		A save method to store the arraylist of Players into the the txt file
	 * 		A method to search for a player based their name
	 * 		A method to find the top player(s)
	 * Depending on your design, you may need and can add more methods
	 */

	// The file that we are working with
	private final String FILE_PATH = "res/casinoInfo.txt";

	//Players object is initialized from the player class
	ArrayList<Player> players;	//The players information will go into this ArrayList
	Menu menuApp;				//Menu object is initialized from Menu
	
	
	//Constructor for the GameManager class
	public GameManager() throws FileNotFoundException {
		players = new ArrayList<>();	 // used to instantiate players
		menuApp = new Menu();			 // used to instantitate menu
		loadData();						 // checks if we have a file and then loads it into an ArrayList
	}
	/**
	 * This method will start the game and display the main menu
	 * The method various calls on methods of menuApp object to display the menu and stored within the option variable. 
	 * The switch statement used will determine the action to take based on the value of option. 
	 * @throws FileNotFoundException
	 */
	public void launchGameApplication() throws IOException {

		boolean flag = true;
		char option;

		while(flag) {
			option = menuApp.mainMenuScreen();
			switch(option) {
				case 'p':
					playGame();
					break;
				case 's':
					search();
					break;
				case 'e':
					save();
					menuApp.saveAndExit();
					flag = false;
					break; 
			}
		}
	}

	/**
	 * This method will direct the user onto to the subMenuScreen, which they will have the option to search for a name, find the top player or go back to the main menu screen
	 * The method various calls on methods of menuApp object to display the menu and stored within the option variable. 
	 * The switch statement used will determine the action to take based on the value of option. 
	 * @throws FileNotFoundException
	 */
	private void search() throws FileNotFoundException {
		char option = menuApp.subMenuScreen();
		
		switch (option) {
			case 't':
				ArrayList<Player> topPlayers = topPlayer();
					menuApp.topPlayer(topPlayers);
				break;
			case 'n':
				String name = menuApp.enterName();
				Player player = searchForName(name);
				if (player == null) {			//If the player does not exist
					menuApp.playerNotFound();	//Displays player not found message
					menuApp.mainMenuScreen();	//Returns the user to the main menu screen
				}
				else {
					menuApp.searchPlayers(player);	//If the player does exist show the player name, balance and wins
				}
				break;
			case 'b':
				menuApp.back();
				break;
		}
		
	}
	
	/**
	 * This method represents the main game loop for our blackjack game. 
	 * it manages the player objects, playing the game, and updating the players balance and wins based on the outcome of each game
	 */
	private void playGame() throws FileNotFoundException {
		String name = menuApp.enterName();
		Player player = searchForName(name);

		boolean exists = true;
		if (player == null) {	//if user doesn't exists
			player = new Player (name, 100, 0);		//Create a new player with a normal balance of $100
			players.add(player);
			exists = false;
		}
		menuApp.showPlayer(player, exists);
		boolean flag = true;
		while(flag) {
			if (player.getBalance() > 0) {		//Checks if the player is eligible to play by seeing if they have more than $0
				BlackJackGame blackJackGame = new BlackJackGame(player);	
				blackJackGame.game(name);		//This will start the game
				flag = menuApp.playAgain();		// This will ask the user if they want to play again
			}
			else {
					flag = false;	//If the player has insufficient funds, terminate the game
					menuApp.insufficientFunds(); //Inform the user has insufficient funds
				}
			}
		}
	
	/**
	 * This method returns an ArrayList of Player objects containing all the players who have the highest number of wins.
	 * Initialize the varabile topScore to 0 which is used to keep track of the top scores.
	 * The for loop iterates over the players list and whoever's score is higher will then takeover the topScore variable, which will then be added to the topPlayers ArrayList
	 * @return an ArrayList of the top scoring players 
	 */	
	private ArrayList<Player> topPlayer() {
		int topScore = 0;
		for (Player player : players) {
			if (player.getWins() > topScore) {
				topScore = player.getWins();
			}
		}
		ArrayList<Player> topPlayers = new ArrayList<>();
		for (Player player : players) {
			if (player.getWins() == topScore) {
				topPlayers.add(player);
			}
		}
		return topPlayers;
	}

	/**
	 * The method takes a string argument and searches for a Player object with a matching name within an Arraylist of Player called players.
	 * The for loop iterates over each player object in the players list, and for each Player object, it will check if the name matches the name attribute of the current Player object.
	 * If a match is found, the reference to the Player object is stored in the player variable and the loop will exit.
	 * If no match is found the user will be informed the player doesn't exist and then sent back to main menu
	 * @param name - the name the user inputted
	 * @return A player object
	 */
	private Player searchForName(String name) throws FileNotFoundException {
		Player player = null;

		for (Player p: players) {
			if (p.getName().equalsIgnoreCase(name)) {
				player = p;
				break;
			}
		}
		return player;
	}

	
	/**
	 * This method writes the player data (name, balance, and # of wins) to the casinoInfo.txt file 
	 * @throws IOException
	 */
	private void save() throws IOException {
		File file = new File(FILE_PATH);	// Initialize the file to access
		PrintWriter writer = new PrintWriter(file);
		for (Player player : players) {	
			writer.println(player.getName() + "," + player.getBalance() + "," + player.getWins());
		}
		writer.close();
	}

	/**
	 * This method reads data from the file, and saves it into a players array list of player objects 
	 * @throws FileNotFoundException
	 */
	private void loadData() throws FileNotFoundException { //check whether file exists
		File db = new File(FILE_PATH);
		String currentLine;
		String[] splittedLine;
		
		if (db.exists()) {
			Scanner fileReader = new Scanner(db);
			
			while (fileReader.hasNextLine()){ 
				
				currentLine = fileReader.nextLine(); //read current line and put it into an string
				splittedLine = currentLine.split(","); // reads each line and "split" or chop ";" of each line into string array list.
				//new object "p" is made from Player class
				Player p = new Player(splittedLine[0], Integer.parseInt(splittedLine[1]), Integer.parseInt(splittedLine[2])); //Player class takes name, balance, numOfWins, which is each item of the array list.
																											// Since the third item "numOfWins" is an integer type in the player class, we must convert
																											// item to an String, because the array list "splittedLine" is a String type. 
				players.add(p); // add Player p to array List "players"
				
			}
			fileReader.close();
		}

	}
}