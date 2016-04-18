
public class PlayingCard implements Comparable<PlayingCard> {

	// WRITE YOUR FIELDS, METHODS AND JAVADOC COMMENTS HERE:
	private int cardRank;
	private char cardSuit;
	
	public PlayingCard(char cardSuit, int cardRank) {
		this.cardSuit = cardSuit;
		this.cardRank = cardRank;
	}
	
	public int compareTo(PlayingCard otherCard) {
		return this.cardRank - otherCard.getRank();
	}
	
	public String getImageFileName() {
		return ("" + this.getRank()) + ("" + this.getSuit()) + ".png";
	}
	
	public int getRank() {
		return this.cardRank;
	}
	
	public char getSuit() {
		return this.cardSuit;
	}
	
	@Override
	public String toString() {
		return ("" + this.getRank()) + ("" + this.getSuit());
	}
	
}
