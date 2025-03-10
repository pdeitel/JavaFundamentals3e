// Fig. 5.4: Craps.java
// Craps class simulates the dice game craps.
import java.util.random.RandomGenerator;

public class Craps {
   // get the default RandomGenerator for use in method rollDice
   private static final RandomGenerator randomNumbers =
      RandomGenerator.getDefault();

   // enum type with constants that represent the game status
   private enum Status {CONTINUE, WON, LOST};                

   // plays one game of craps
   public static void main(String[] args) {
      int myPoint = 0; // point if no win or loss on first roll
      Status gameStatus; // can contain CONTINUE, WON or LOST

      int sumOfDice = rollDice(); // first roll of the dice

      // determine game status and point based on first roll 
      switch (sumOfDice) {
         case 7:  // win with 7 on first roll    
         case 11: // win with 11 on first roll
            gameStatus = Status.WON;
            break;
         case 2: // lose with 2 on first roll
         case 3: // lose with 3 on first roll      
         case 12: // lose with 12 on first roll
            gameStatus = Status.LOST;
            break;
         default: // did not win or lose, so remember point  
            gameStatus = Status.CONTINUE; // game is not over
            myPoint = sumOfDice; // remember the point       
            System.out.printf("Point is %d%n", myPoint);
            break; 
      }

      // while game is not complete
      while (gameStatus == Status.CONTINUE) { // not WON or LOST
         sumOfDice = rollDice(); // roll dice again

         // determine game status
         if (sumOfDice == myPoint) { // win by making point
            gameStatus = Status.WON;
         } 
         else { 
            if (sumOfDice == 7) { // lose by rolling 7 before point
               gameStatus = Status.LOST;
            } 
         } 
      } 

      // display won or lost message
      if (gameStatus == Status.WON) {
         System.out.println("Player wins");
      } 
      else {
         System.out.println("Player loses");
      } 
   } 

   // roll dice, calculate sum and display results
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
