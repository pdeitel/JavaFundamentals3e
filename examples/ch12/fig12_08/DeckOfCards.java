// Fig. 12.8: DeckOfCards.java
// Card shuffling and dealing with Collections method shuffle.
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

// class to represent a Card in a deck of cards
class Card {
   public enum Face {Ace, Deuce, Three, Four, Five, Six,
      Seven, Eight, Nine, Ten, Jack, Queen, King }      
   public enum Suit {Clubs, Diamonds, Hearts, Spades} 

   private final Face face; 
   private final Suit suit; 
   
   // constructor
   public Card(Face face, Suit suit) {
       this.face = face; 
       this.suit = suit; 
   } 
   
   // return face of the card
   public Face getFace() {return face;}

   // return suit of Card
   public Suit getSuit() {return suit;}

   // return String representation of Card
   public String toString() {
      return String.format("%s of %s", face, suit);
   } 
} 

// class DeckOfCards declaration
public class DeckOfCards {
   private List<Card> deck = new ArrayList<>(); // List to store Cards

   // set up deck of Cards and shuffle
   public DeckOfCards() {
      // populate deck with Card objects
      for (Card.Suit suit : Card.Suit.values()) {
         for (Card.Face face : Card.Face.values()) {  
            deck.add(new Card(face, suit));
         } 
      } 

      Collections.shuffle(deck);  // shuffle deck
   } 

   // output deck
   public void printCards() {
      // display 52 cards in four columns
      for (int i = 0; i < deck.size(); ++i) {
         System.out.printf("%-19s%s", deck.get(i),
            ((i + 1) % 4 == 0) ? System.lineSeparator() : "");
      }
   } 

   public static void main(String[] args) {
      DeckOfCards cards = new DeckOfCards();
      cards.printCards();
   }   
} 


/**************************************************************************
 * (C) Copyright 1992-2025 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
