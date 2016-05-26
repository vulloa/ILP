/*
   Hand Class from Assignment #3
   added methods
*/

public class Hand {
   // constants
   public static final int MAX_CARDS = 52;
   
   // private variables
   private Card[] myCards;
   public int numCards;
   
   /*
      Hand()
      Default constructor for class
      creates and instantiates an array (myCards)
      calls resetHand method
   */
   Hand(){
      myCards = new Card[MAX_CARDS];
      resetHand();
   }
   
   // METHODS
   
   /*
      resetHand()
      resets the myCards array with null values
      resets numCards to 0
   */
   public void resetHand(){
      for(int x = 0 ; x < MAX_CARDS ; x++)
         myCards[x] = null;
      
      numCards = -1;  
   }
   
   /*
      takeCard()
      adds a card object to Hand (array of card objects)
      cannot exceed MAX_CARDS, if it does then return false
      increment numCards if true
   */
   public boolean takeCard(Card card){
      if(numCards < MAX_CARDS){
         numCards++;
         myCards[numCards] = card;
         return true;
      }

      return false;
   }



   
   /*
      playCard()
      to "play" a card, it is removed from the hand.
      the "removed" card is replaced with a null value
      numCards is decremented
   */


   public Card playCard(int cardIndex){
      if ( numCards == 0 ){
         //Creates a card that does not work
         return new Card('M', Card.Suit.SPADES);
      }
      //Decreases numCards.
      Card card = myCards[cardIndex];
      
      for(int i = cardIndex; i < numCards; i++){
         myCards[i] = myCards[i+1];
      }

      myCards[numCards] = null; 
      numCards--;    
      return card;
    }


   /*
      toString()
      method that takes a Card and is able to print the values in a String
      if hand is empty, then show empty space
   */
   public String toString(){      
      if(numCards == 0)
         return "My Hand:[]";
      
      String myHand = "My Hand: [ ";
      
      for(int x = 0; x <= numCards ; x++)
         myHand += myCards[x].toString() + ", ";

      return myHand + " ]";
   }
   
   /*
      getNumCards()
      accessor for numCards value
   */
   public int getNumCards()   {  return numCards;  }
   
   /*
      inspectCard(int)
      method is used to view a card in a Hand and return 
      either a copy(clone) of hte card or the default Ace of Spades
   */
   public Card inspectCard(int k)
   {
      //inspect cards to check for legal values
      
      if(k > this.numCards || k < 0)
         return new Card('Z',Card.Suit.SPADES);
      else
         return myCards[k].clone();
   }
   
   // ADDED METHODS
   
   /*
      void sort()
      added method, uses arraySort method from the Card class
   */
   public void sort()   {  Card.arraySort(myCards, numCards+1);  }
   
   
}  // end class
