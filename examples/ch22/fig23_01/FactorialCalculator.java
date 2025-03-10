// Fig. 22.1: FactorialCalculator.java
// Recursive factorial method.
import java.math.BigInteger;

public class FactorialCalculator {
   // recursive method factorial (assumes its parameter is >= 0)
   public static BigInteger factorial(int number) {                  
      if (number <= 1) { // test for base case                  
         return BigInteger.ONE; // base cases: 0! = 1 and 1! = 1
      }                                                         
      else { // recursion step                                  
         return BigInteger.valueOf(number).multiply(
            factorial(number - 1));
      }
   }

   public static void main(String[] args) {
      // calculate the factorials of 0 through 10
      for (int counter = 0; counter <= 10; ++counter) {
         System.out.printf("%d! = %d%n", counter, factorial(counter));
      } 

      // display the factorials of 20, 30 and 40
      System.out.printf("%n20!: %s%n30!: %s%n40!: %s%n", 
         factorial(20), factorial(30), factorial(40));
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

