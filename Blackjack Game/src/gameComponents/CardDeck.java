package gameComponents;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class represents a card deck
 */
public class CardDeck {
	
	/**
	 * deck holds all of the cards that currently are in the current deck
	 */
	private ArrayList <Card> deck;
	
	/**
	 * This constructor initiate the arraylist and calls the repective methods to create a new deck
	 */
	public CardDeck() {
		this.deck = new ArrayList<Card>();
		this.reset();
	}

	/** call this method to reset the deck (fill with 52 cards and shuffle) */
	public void reset() {
		this.fillDeck();
		this.shuffleDeck();
	}

	/**
	 * This private method creates the deck
	 */
	private void fillDeck() {
		// suits holds the names of the suits
		String[] suits = {"Spades", "Diamonds", "Clubs", "Hearts"};
		
		//nested loops to fill the deck with each card (every combination of suit and rank)
		for (String suit: suits) {
			for (int rank = 1; rank <=13; rank++) {
				this.deck.add(new Card(rank, suit));
			}
		}
	}

	/**
	 * this private method shuffles the deck (randomizes card order)
	 */
	private void shuffleDeck() {
		Collections.shuffle(this.deck); 
	}

	/**
	 * Check if the deck is currently empty
	 * @return true if the deck has no cards left, false otherwise
	 */
	public boolean isEmpty() {
		return this.deck.size() == 0;
	}

	/**
	 * this method deals a card
	 * it removes the first card from the deck arrayList, and returns it
	 * @return a Card object
	 * @throws IndexOutOfBoundsException if the deck is empty when dealCard() is called
	 */
	public Card dealCard() throws IndexOutOfBoundsException {
		return this.deck.remove(0);
	}
}
