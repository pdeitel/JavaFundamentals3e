// Fig. 5.2: RandomIntegers.java
// Rolling a six-sided die.
import java.util.random.RandomGenerator; 

public class RandomIntegers {
   public static void main(String[] args) {
      // randomNumbers object will produce random numbers
      RandomGenerator randomNumbers = RandomGenerator.getDefault(); 

      // loop 10 times
      for (int counter = 1; counter <= 10; ++counter) {
         // pick random integer from 1 to 6    
         int face = randomNumbers.nextInt(1, 7);

         System.out.printf("%d  ", face); // display generated value
      }

      System.out.println();
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
