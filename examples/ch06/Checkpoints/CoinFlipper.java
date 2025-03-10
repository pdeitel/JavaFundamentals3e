// Section 6.10, Checkpoint 1: CoinFlipper.java
// Summarizing coin flips.
import java.util.random.RandomGenerator;

public class CoinFlipper {
   public static void main(String[] args) {
      RandomGenerator randomNumbers = RandomGenerator.getDefault();

      // constants representing heads and tails
      final int HEADS = 0;
      final int TAILS = 1;

      // frequency[0] for heads, frequencies[1] for tails
      int[] frequency = new int[2]; 

      // simulate 100 coin flips
      for (int counter = 0; counter < 100; ++counter) {
         ++frequency[randomNumbers.nextInt(2)];
      }

      System.out.printf("Number of heads: %d%n", frequency[HEADS]);
      System.out.printf("Number of tails: %d%n", frequency[TAILS]);
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
