// Fig. 5.4: CrapsWithSwitchExpressions.java
// Craps class simulates the dice game craps.
import java.util.random.RandomGenerator;

public class CrapsWithSwitchExpressions {
   // Create a random number generator for use in method rollDice
   private static final RandomGenerator randomNumbers = 
      RandomGenerator.getDefault();

   // enum type with constants that represent the game status
   private enum Status {CONTINUE, WON, LOST}

   // plays one game of craps
   public static void main(String[] args) {
      int myPoint = 0; // point if no win or loss on first roll

      int sumOfDice = rollDice(); // first roll

      // determine game status and point based on first roll 
      Status gameStatus = switch (sumOfDice) {
         case 7, 11 -> Status.WON;
         case 2, 3, 12 -> Status.LOST;
         default -> {
            myPoint = sumOfDice; // remember the point
            System.out.printf("Point is %d%n", myPoint);
            yield Status.CONTINUE; // game is not over
         }
      };

      // while game is not complete
      while (gameStatus == Status.CONTINUE) { // not WON or LOST
         sumOfDice = rollDice(); // roll dice again

         // determine game status 
         gameStatus = switch (sumOfDice) {
            case 7 -> Status.LOST;
            default -> sumOfDice == myPoint ? 
               Status.WON : Status.CONTINUE;
         };
      }

      // display won or lost message
      if (gameStatus == Status.WON) {
         System.out.println("Player wins");
      } 
      else {
         System.out.println("Player loses");
      } 
   }

   public static int rollDice() {
      // pick random die values
      int die1 = randomNumbers.nextInt(1, 7); // first die roll
      int die2 = randomNumbers.nextInt(1, 7); // second die roll

      int sum = die1 + die2; // sum of die values

      // display results of this roll
      System.out.printf("Player rolled %d + %d = %d%n", die1, die2, sum);

      return sum; 
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
