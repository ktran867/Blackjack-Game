package saveableObjects;

public class Player {
	
	/**
	 * This class represent a player, as recorded in the Database
	 */
	/**
	 * @param name - The name of the player
	 * @param balance - the total balance in the players account
	 * @param wins - the total # of wins the player has 
	 */
	private String name;
	private int balance;
	private int wins;

	//Constructor for the player class
	public Player(String name, int balance, int wins) {
		this.name = name;
		this.balance = balance;
		this.wins = wins;
	}

	/**
	 * A getter method to get the users name
	 * @return - name of the player 
	 */
	public String getName() {
		return name;
	}

	/**
	 * A getter method to get the users balance
	 * @return - the balance of the player 
	 */
	public int getBalance() {
		return balance;
	}

	/**
	 * A getter method to get the users wins
	 * @return the wins of the player 
	 */
	public int getWins() {
		return wins;
	}

	/**
	 * A setter method for balance remaining for the player 
	 * @param balance - the remaining balance for the player
	 */
	public void setBalance(int balance) {
		this.balance = balance;
	}

	/**
	 * A setter method for number of wins for the player
	 * @param wins - The number of wins the player has
	 */
	public void setWins(int wins) {
		this.wins = wins;
	}

	/**
	 * A method to format the output 
	 * @param existing
	 * @return a formatted output
	 */
	public String format(boolean existing) {
		if (existing) { 
			return String.format("*** %-30s --- %-25s ***", "Welcome Back! " + name, "Your balance is $" + balance);

		}
		return String.format("*** %-30s --- %-24s ***", "Welcome " + name, "Your balance is $" + balance);
	}
	
}
