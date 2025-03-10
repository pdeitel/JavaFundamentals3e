// Fig. 3.5: UsingBigInteger.java
// Creating and manipulating super-sized integers
// with objects of the Java API's BigInteger class.
import java.math.BigInteger;

public class UsingBigInteger {
   public static void main(String[] args) {
      // display the largest value a long can store
      System.out.printf("LARGEST LONG VALUE: %d%n%n", Long.MAX_VALUE);

      // can create BigIntegers from Strings or integers
      BigInteger value1 = 
         new BigInteger("100000000000000000000000000000"); // 30 digits
      BigInteger value2 = BigInteger.valueOf(9_223_372_036_854_775_807L);
      BigInteger value3 = BigInteger.valueOf(17); 

      System.out.println("INITIAL VALUES");
      System.out.printf("BigInteger value1: %s%n", value1);
      System.out.printf("BigInteger value2: %s%n", value2);
      System.out.printf("BigInteger value3: %s%n", value3);

      System.out.println("\nADD, SUBTRACT AND MULTIPLY BIGINTEGERS");
      System.out.printf("     value1.add(value2): %s%n", 
         value1.add(value2));
      System.out.printf("value1.subtract(value2): %s%n", 
         value1.subtract(value2));
      System.out.printf("value1.multiply(value2): %s%n", 
         value1.multiply(value2));
      System.out.printf("value1.multiply(value3): %s%n", 
         value1.multiply(value3));
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
