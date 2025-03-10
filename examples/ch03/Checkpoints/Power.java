// Section 3.7, Checkpoint 1: Power.java
// Calculate the first power of 2 greater than 1,000,000. 
public class Power {
   public static void main(String[] args) {
      int product = 2;
      int n = 1;

      while (product <= 1_000_000) {
         product = 2 * product;
         n = n + 1;
      }

      System.out.printf("%s %d%n%s %d%n", 
         "First power of 2 greater than 1,000,000 is", product, 
         "which is 2 to the power of", n);
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