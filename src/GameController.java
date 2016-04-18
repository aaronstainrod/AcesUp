import java.util.*;

public class GameController {

	// DO NOT CHANGE OR ADD FIELDS BELOW.
	// YOU MUST USE THESE FIELDS TO IMPLEMENT YOUR GAME.

	private ArrayList<PlayingCard> deck;
	private ArrayList<PlayingCard>[] list;
	private ArrayList<PlayingCard> discardPile;
	private static int playerScore;
	
	// WRITE YOUR CODE AND JAVADOC HERE:
	private final char CLUBS = 'C';
	private final char DIAMONDS = 'D';
	private final char HEARTS = 'H';
	private final char SPADES = 'S';
	private final char[] suits = new char[]{CLUBS, DIAMONDS, HEARTS, SPADES};
	private PlayingCard playingCard;
	
	//CONSTRUCTOR	
	public GameController() {
			//Initializes deck
			deck = new ArrayList<>();			
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 13; j++) {
					playingCard = new PlayingCard(suits[i], j+1);
					deck.add(playingCard);
				}
			}
			
			//Initializes four empty card lists
			list = new ArrayList[4];
			for (int i = 0; i < list.length; i++) {
				list[i] = new ArrayList<PlayingCard>();
			}
			
			//Initializes discard pile
			discardPile = new ArrayList<PlayingCard>();
			
		}
	
	//METHODS
	public PlayingCard getCard(int listNum, int index) {
		
		//If selected list is out of bounds, returns null
		if (listNum <= -1 || listNum >= 4) {
			return null;
		}
		
		/*If the selected card in the selected list is out of bounds,
		 * returns null
		 */
		if (index <= -1 || index >= list[listNum].size()) {
			return null;
		}
		
		/*If the selected list is empty or the card index is invalid,
		 * returns null
		 */
		if (list[listNum] == null || list[listNum].get(index) == null) {
			return null;
		}
		
		//Represents the selected card
		PlayingCard card = list[listNum].get(index);
		
		//Returns the selected card from the selected list
		return card;
	}
	public static int getScore() {
		
		//Returns the player's score
		return playerScore;
		
	}
	public void deal() {
		
		/*If the deck is not empty, the first card on the
		 * deck is added to the end of each list in the 
		 * PlayingCard list array
		 */
		for (int i = 0; i < list.length; i++) {
			if (!deck.isEmpty()) {
				list[i].add(deck.get(0));
				deck.remove(0);			
			} else {
				break;
			}
		}
	}
	public void discard(int listNum) {	
		
		//If listNum is out of bounds, the method does nothing
		if (listNum <= -1 || listNum >= 4) {
			return;
		}
		
		//Represents the currently selected list
		ArrayList<PlayingCard> currList = new ArrayList<>();
		currList = list[listNum];
		
		//Represents the last card in the selected list
		PlayingCard currCard = currList.get(currList.size()-1);
		
		//If the card is an Ace, the discard method cannot operate
		if (currCard.getRank() == 0) {
			return;
		}
		
		/*Represents all of the last cards on each card list with
		 *the same suit, with the exception of the selected card
		 */
		ArrayList<PlayingCard> lastSameSuitCards = new ArrayList<>();
		for (int i = 0; i < list.length; i++) {
			PlayingCard lastCard = list[i].get(list[i].size()-1);
			if (lastCard.getSuit() == currCard.getSuit() && lastCard != currCard) {
				lastSameSuitCards.add(list[i].get(list[i].size()-1));
			}
		}
		
		/*If the "last card" list is not empty,
		 *if any of the cards in the list is a higher rank than
		 *the current card, then the discard method is unable to
		 *complete. Otherwise, the card is removed from its list
		 *and added to the discard pile
		 */
		if (!lastSameSuitCards.isEmpty()) {
			for (PlayingCard pc: lastSameSuitCards) {
				if (pc.getRank() > currCard.getRank()) {
				} else {
					discardPile.add(currCard);
					currList.remove(currList.size()-1);
					playerScore += currCard.getRank();
				}
			}
		}

	}
	public void move(int listNum) {
		
		//If the selected list is empty, the method does nothing
		if (list[listNum].isEmpty()) {
			return;
		}
		
		//Represents last card in the selected list
		PlayingCard lastCard = list[listNum].get(list[listNum].size()-1);
		
		/*Traverses all PlayingCard lists and checks for empty lists.
		 * Places the last card into the first empty list detected 
		 */
		for (ArrayList<PlayingCard> pcList: list) {
			if (pcList.isEmpty()) {
				pcList.add(lastCard);
				list[listNum].remove(list[listNum].size()-1);
				break;
			}
		}
	}
	public void startNewGame() {
		
		/*If the card lists are not empty, all of their contents
		 * are added to the deck and the list is cleared
		 */
		for (int i = 0; i < list.length; i++) {
			if (!list[i].isEmpty()) {
				deck.addAll(0, list[i]);
				list[i].clear();
			}
		}
		
		/*Adds all cards from the discard pile to the deck.
		 * Removes all cards from the discard pile
		 */
		deck.addAll(0, discardPile);
		discardPile.clear();
		
		//Shuffles the deck
		Collections.shuffle(deck);
		
		//Resets player score
		playerScore = 0;
		
		//ADD START GAME EXPRESSIONS?
		deal();
		
	}

}

