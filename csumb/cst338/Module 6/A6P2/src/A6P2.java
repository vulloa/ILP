/*
   CST 338 - MODULE 6
   Assignment 6
   Group:
      +  Matthew Bichay
      +  Julia Diliberto
      +  Vanessa Ulloa
      +  James O'Dea
   Phase 2 : Multithreading Implimentation
   -  Add a timer to High Card Game
   -  Timer class (extends Thread) - overrides the run() method
      +  Display the timer box and numbers
      +  Create start and stop buttons to control the timer
*/

public class A6P2 
{

      public static final int PLAYER_COUNT = 2;
      public static final int HAND_CARD_COUNT = 7;
      public static final int PACK_COUNT = 1;
      public static final int JOKER_COUNT = 0;
      public static final int UNUSED_CARD_COUNT = 0;
      public static final String GAME_NAME = "High Card Game:  By - Matt, "
            + "Julia, Vanessa, and James";

      public static void main(String args[])
      {
         CardGameFramework highCardFramework = new CardGameFramework(PACK_COUNT, JOKER_COUNT, UNUSED_CARD_COUNT, null,
            PLAYER_COUNT, HAND_CARD_COUNT);

         CardTable gameTable = new CardTable(GAME_NAME, HAND_CARD_COUNT, PLAYER_COUNT);
         
         CardGameController highCardController = new CardGameController(highCardFramework, gameTable);
         highCardController.start();
      }

}  // end class