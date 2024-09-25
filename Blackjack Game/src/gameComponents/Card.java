package gameComponents;

/**
 * This class represents a card
 */
public class Card {

	/**
	 * rank represents the rank of the current card
	 */
	private int rank; 		// the card's rank (Ace through King, with 1=ace, 11=jack, 12=queen, 13=king)
	private String suit; 	// the card's suit ("Spades", "Diamonds", "Clubs", or "Hearts")

	/**
	 * This constructor sets the rank and suit of card
	 * @param rank card rank (1 - 13)
	 * @param suit the card's suit ("Spades", "Diamonds", "Clubs", or "Hearts")
	 */
	public Card(int rank, String suit) {
		this.rank = rank;
		this.suit = suit;
	}

	/**
	 * rank getter method
	 * @return the rank
	 */
	public int getRank() {
		return this.rank;
	}

	/**
	 * suit getter method
	 * @return the suit
	 */
	public String getSuit() {
		return this.suit;
	}

	/**
	 * Returns a string representation of the card for printing
	 */
	public String toString() {
		
		if (this.rank == 1) {
			return "A" + " of " + this.suit;
		}
		else if (this.rank == 11) {
			return "J" + " of " + this.suit;
		}
		else if (this.rank == 12) {
			String t =  "Q" + " of " + this.suit;
			return t;
		}
		else if (this.rank == 13) {
			return "K" + " of " + this.suit;
		}
		else {
			return this.rank + " of " + this.suit;
		}
	}
}
