/*
   CST 338 - MODULE 6
   Assignment 6
   Group:
      +  Matthew Bichay
      +  Julia Diliberto
      +  Vanessa Ulloa
      +  James O'Dea
   Phase 3 : Make a new Game!
	1.	Take turns with the computer putting a card on one of two stacks in the middle of the table.  
	2.	You can put on a card that is one value higher or one value lower.  (6 on a 5 OR 4 on a 5, Q on a J OR T on a J, etc.) 
    3.	After you play, you get another card from the deck in your hand.
    4.	Keep going until the single deck is out of cards.
    5.	If you cannot play, click a button that says "I cannot play".  The the computer gets a second turn.  Same for you, if the computer cannot play.  If neither of you can play, then the deck puts a new card on each stack in the middle of the table.
    6.	Who ever has the least number of "cannot plays", is the winner.  Declare this at the end, when the deck is exhausted.
*/

public class A6P3 {

		public static final int PLAYER_COUNT = 2;
		public static final int HAND_CARD_COUNT = 7;
		public static final int PACK_COUNT = 1;
		public static final int JOKER_COUNT = 0;
		public static final int UNUSED_CARD_COUNT = 0;
		public static final String GAME_NAME = "Build Game: By - Matt, Julia, Vanessa, and James";

		public static void main(String args[]){

			CardGameFramework buildFramework = new CardGameFramework(
				PACK_COUNT, JOKER_COUNT, UNUSED_CARD_COUNT, null,
				PLAYER_COUNT, HAND_CARD_COUNT);

			CardTable buildTable = new CardTable(GAME_NAME, HAND_CARD_COUNT, PLAYER_COUNT);
			CardGameController buildController = new CardGameController(buildFramework, buildTable);
			buildController.start();
	}
}