/*
   public class GUICard
   class that manages the reading and the building of the card image Icons. 
   This class is used to produce an image icon where the cilent needs one. 
*/

import java.awt.*;
import javax.swing.*;

public class GUICard 
{
   // VARIABLES

   /*
      iconCards[][] - array that holds the icons(images) for the cards
      iconBack - back of playcard for the Computer's hand
      iconsLoaded - boolean variable that is set true when the cards are loaded into the array
   */
   private static Icon[][] iconCards = new ImageIcon[14][4];
   private static Icon iconBack;
   static boolean iconsLoaded = false;
   
   // METHODS
   /*
      loadCardIcons()
      stores icons in a 2-D array
      see Phase 1
      Check iconsLoaded to determine whether to call method
      The 2-D array is loaded with the file names, 
      seperate image icon for the back of the card - then iconsLoaded is true
   */
   static void loadCardIcons(){
      String suit, cValue;
      if(!iconsLoaded){
         for(int suits = 0 ; suits < 4 ; suits++){
            for(int values = 0 ; values <= 13 ; values++){
               suit = intToCardSuit(suits);
               cValue = intToCardValue(values);
               iconCards[values][suits] = new ImageIcon("images/" + cValue + suit + ".gif");
            }
         }
      }
      iconBack = new ImageIcon("images/BK.gif");
      iconsLoaded = true;
   }
   
   /*
      turnIntIntoCardValue(int)
      String method that turns 0 - 12 into an appropriate card value
   */
   static String intToCardValue(int k){
      char[] cardValues = {'2','3','4','5','6','7','8','9','T','J','Q','K','A','X'};
   
      if(k >= 0 && k <= 13)
         return Character.toString(cardValues[k]);
      else{
         System.out.println("invalid Card value");
         return Character.toString(cardValues[0]);
      }
   
   }
   
   /*
      turnIntIntoCardSuit(int)
      String method that takes 0 - 3 and returns an appropriate suit. 
   */
   static String intToCardSuit(int j){
      char[] suitValues = {'S','H','D','C'};
   
      if( j >= 0 && j <= 3)
         return Character.toString(suitValues[j]);
      else{
         System.out.println("invalid suit value");
         return Character.toString(suitValues[0]);
      }
   
   }
   
   /*
      getIcon(Card)
      call loadCardIcons()
      takes Card object from the client, returns the Icon for that Card. 
   */
   static public Icon getIcon(Card card){  
      loadCardIcons();
      return iconCards[card.getValue().asInt()-2][card.getSuit().asInt()];
   }
   

   /*
      getBackCardIcon()
      acesssor for after iconBack is populated with correct filename
   */
   static public Icon getBackCardIcon(){ 
      loadCardIcons(); 
      return iconBack;
   }


   static public JLabel asJLabel(Icon image){
      return new JLabel(image);
   }

}