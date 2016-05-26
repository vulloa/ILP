public class Build {

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