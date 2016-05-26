import java.util.HashMap;
import java.util.Map;
/*
   Card class from Assignment #3
   + Added Joker suit and value
   + added methods for card comparison
*/

public class Card 
{
   enum Suit {
       SPADES      ("Spades"),
       HEARTS      ("Hearts"),
       DIAMONDS    ("Diamonds"),
       CLUBS       ("Clubs");

       // Did this to add a toString and give the enum a value.
       private final String suit;
       private Suit(final String newSuit) {suit = newSuit;}

       public String asString() {return suit;}

       public int asInt() {
         switch(suit) {
            case "Spades": return 0;
            case "Hearts": return 1;
            case "Diamonds": return 2;
            default: return 3;
         }
       }

   }

   enum Value {
       TWO     ('2'),
       THREE   ('3'),
       FOUR    ('4'),
       FIVE    ('5'),
       SIX     ('6'),
       SEVEN   ('7'),
       EIGHT   ('8'),
       NINE    ('9'),
       TEN     ('T'),
       JACK    ('J'),
       QUEEN   ('Q'),
       KING    ('K'),
       ACE     ('A'),
       JOKER   ('X');

       // Did all this below to add a toString and give all the enum elements
       // a value, this can help for playing games with numbers and printin
       // strings.
       private final char value;
         private static Map<Character, Value> valueMap;

         private static void initializeMap() {
            valueMap =  new HashMap<Character, Value>();
            for (Value v : Value.values()) {
               valueMap.put(v.value, v);
            }
         }



       private Value(final char newValue) { value = newValue; }


       public int asInt() { 
         switch(value) {
            case 'T': return 10;
            case 'J': return 11;
            case 'Q': return 12;
            case 'K': return 13;
            case 'A': return 14;
            case 'X': return 15;
            default: return Character.getNumericValue(value);
         }

       }

       public char asChar() { return value; }


       public static Value fromChar(char c){
         if (valueMap == null)
            initializeMap();
         Value value = valueMap.get(c);
         if (valueMap.containsKey(c))
            return valueMap.get(c);
         return null;
       }
   }
   
   
   // variables
   private Value value;
   private Suit suit;
   private Boolean errorFlag;
   
   // constructors
   
   /*
      Card()
      Card(char,Suit)
      Card(Value,Suit)
      Constructors for the class
   */
   Card(char value, Suit suit)  { this.set(value,suit); }
   
   Card() { set('A', Suit.SPADES);  }  
   
   Card(Value value, Suit suit) { this.set(value.asChar(),suit);  }
   
   @Override
   public Card clone()  { return new Card(this.value.asChar(),this.suit); }
   
   
   // methods
    public String toString(){
      if(getErrorFlag())
         return "** illegal card entry **";

      return getValue().asChar() + " of " + getSuit().asString();
   }
    
    public boolean set(char value, Suit suit) {
       this.value = Value.fromChar(Character.toUpperCase(value));
       if (this.value == null){
         this.errorFlag = true;
         return false;
       }
       this.suit = suit;
       this.errorFlag = false;
       return true;    
    }
    
    public Suit getSuit() { return this.suit; }
    
    public Value getValue() { return this.value; }
    
    public boolean getErrorFlag() {  return this.errorFlag; }


    public static void arraySort(Card[] cards,int arraySize){
       Boolean itsGoTime = true;
       Card temp;

       //At best, this is an O(n) sorting method...
       //At worst, its an O(n^2)
       while (itsGoTime) {
         itsGoTime = false;
         for (int i = 0 ; i < arraySize-1; ++i) {
            if (cards[i].getValue().asInt() > cards[i+1].getValue().asInt()) {
               temp = cards[i];
               cards[i] = cards[i+1];
               cards[i+1] = temp;
               itsGoTime = true;
            }
         }
       }
    }

    public Boolean equals(Card card) {
      if (card.getValue().asInt() == this.value.asInt()
         && card.getSuit().asString().equals(this.suit.asString()))
         return true;
      return false;
    }
    
}//   end class