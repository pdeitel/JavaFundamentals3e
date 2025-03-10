// GuessTheNumberGame.java
// Program plays guess the number.
import java.util.Scanner;
import java.util.random.RandomGenerator;
import java.util.random.RandomGeneratorFactory;

public class GuessTheNumberGame {
   private static int answer;
   private static boolean gameOver = false; // is current game finished?

   public static void main(String[] args) {
      // Create a RandomGenerator with a seed so this program produces 
      // a reproducible sequence. To randomize, replace line 16 with
      // RandomGenerator.getDefault();
      RandomGenerator generator = 
         RandomGeneratorFactory.getDefault().create(11);
      Scanner input = new Scanner(System.in);
      boolean continuePlaying = true; // start a new game?

      while (continuePlaying) {
         // Start a new game: reset gameOver flag and choose a new number.
         gameOver = false;
         newGame(generator);

         // Play the current game until the correct guess.
         while (!gameOver) {
            System.out.print("? ");
            checkGuess(input.nextInt());
         }

         // Ask if the user wants to play again.
         System.out.print("Would you like to play again (y or n)? ");
         String playAgain = input.next();

         if (!playAgain.toLowerCase().equals("y")) {
            continuePlaying = false;
         }
      }
   }

   // Starts a new game by generating a new random answer.
   public static void newGame(RandomGenerator generator) {
      answer = generator.nextInt(1, 1001);
      System.out.printf("""
         I have a number between 1 and 1000.
         Can you guess my number?
         Please enter your first guess.
         """);
   }

   // Checks the user's guess and provides feedback.
   public static void checkGuess(int userGuess) {
      if (userGuess < answer) {
         System.out.println("Too low. Try again.");
      } 
      else if (userGuess > answer) {
         System.out.println("Too high. Try again.");
      } 
      else {
         gameOver = true;
         System.out.println("\nExcellent! You guessed the number!");
      }
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
