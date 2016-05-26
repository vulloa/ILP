/*
   Deck class from Assignment #3
   added methods and Joker
   add methods for adding and removing cards from the deck as well as as a sort method
*/
import java.util.Collections;
import java.util.Arrays;

public class Deck 
{
   // CONSTANTS
   public static final int MAX_PACK = 6;
   public static final int MAX_CARDS_IN_PACK = 56;
   public static final int MAX_CARDS = MAX_CARDS_IN_PACK * MAX_PACK;
   
   /*
      masterPack - master deck of cards with no duplicates
   */
   private static Card[] masterPack;
   private Card[] cards;
   private int topCard;
   private int numPacks;
   
   /*
      Deck() - default constructor, calls allocateMasterPack() with numPacks = 1
      Deck(int) - constructor that passes numPacks
   */
   Deck()   {  Deck.allocateMasterPack(); }
   
   Deck(int numPacks)
   {
      Deck.allocateMasterPack();
      
      if (numPacks > MAX_PACK)
         init(MAX_PACK);
      else
         init(numPacks);
   }
   
   // METHODS
   
   /*
      init(int)
      method called in constructor that creates an array from numPacks * packs in a deck
      adds cards to the array until the array is filled
      deck is unshuffled by default
      topCard = last card added to the deck (index of)
   */
   public void init(int numPacks) 
   {  

      cards = new Card[MAX_CARDS_IN_PACK * numPacks];
      
      for(int x = 0; x < cards.length ; ++x){
         cards[x] = Deck.masterPack[x % MAX_CARDS_IN_PACK];
      }
      
      topCard = MAX_CARDS_IN_PACK * numPacks - 1;
   }
   
   /*
      shuffle()
      shuffles the deck (array of cards)
   */
   public void shuffle()   {  Collections.shuffle(Arrays.asList(cards).subList(0,topCard));   }
   
   /*
      dealCard()
      deals a card from the "top" of the deck
      uses topCard index value to get the card value and clone it to return
   */
   public Card dealCard()
   {  
      if(topCard < 0)
      {
         return new Card('Z',Card.Suit.HEARTS);
      }
      else
      {
         Card tCard = cards[topCard].clone();
         topCard--;
         return tCard;
      }
   }
   
   /*
      getTopCard()
      accessor for topCard value
   */
   public int getTopCard() {  return topCard;   }
   
   /*
      inspectCard(int)
      method that receives a Card and inspects it for a valid value
      if is valid then card copy is returned
   */
   public Card inspectCard(int k)
   {
      if(k > topCard || k < 0)
      {
         return new Card('Z',Card.Suit.SPADES);
      }
      else
         return this.cards[k].clone();
   }
   
   /*
      allocateMasterPack()
      method that is used by the Constructor to fill array with Card values
   */
   private static void allocateMasterPack()
   {
      int i = 0;

      if (Deck.masterPack == null)
         Deck.masterPack = new Card[MAX_CARDS_IN_PACK];
      
         for(Card.Suit suit : Card.Suit.values())
            for(Card.Value value : Card.Value.values())
               Deck.masterPack[i++] = new Card(value,suit);
 
   }
   
   // ADDED METHODS
   
   /*
      addCard(Card)
      method used to add a Card to the array of cards
      also updates index of topCard
   */
   boolean addCard(Card card)
   {
      if (topCard + 1 == cards.length)
         return false;
      topCard++;
      cards[topCard] = card.clone();
      return true;
   }
   
   /*
      removeCard(Card)
      method that removes Card from array of Cards
      also updates index of topCard
   */
   boolean removeCard(Card card) 
   {
      for (int i = 0; i < topCard; ++i)
         if (cards[i].equals(card)) 
         {
            cards[i] = cards[topCard];
            topCard--;
            return true;
         }

      return false;
   }
   
   /*
      sort()
      sort method from Card class
   */
   void sort() {  Card.arraySort(cards, getNumCards()); }
   
   /*
      getNumCards()
      accesor for numCards variable
   */
   int getNumCards() {  return topCard+1;   }

}//   end class