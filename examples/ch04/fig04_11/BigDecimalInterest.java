// Fig. 4.11: BigDecimalInterest.java
// Compound-interest example with BigDecimal objects.
import java.math.BigDecimal;
import java.math.RoundingMode;

public class BigDecimalInterest {
   public static void main(String[] args) {
      // create BigDecimal objects from strings
      BigDecimal principal = new BigDecimal("1000.00"); 
      BigDecimal rate = new BigDecimal("0.05"); 

      System.out.printf("Initial principal: %7s%n", principal);
      System.out.printf("    Interest rate: %7s%n%n", rate);

      // display headers
      System.out.printf("%s%17s   %s%n", 
         "Year", "Rounded Amount", "Precise Stored Amount");

      // calculate amount on deposit for each of 10 years
      for (int year = 1; year <= 10; ++year) {
         BigDecimal amount = principal.multiply(
            BigDecimal.ONE.add(rate).pow(year));
         System.out.printf("%4d%17s   %s%n", year,
            amount.setScale(2, RoundingMode.HALF_EVEN), amount);
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
