// Fig. 9.13: PayableInterfaceTest.java
// Payable interface test program processing Invoices and 
// Employees polymorphically.
import java.math.BigDecimal;
import java.math.RoundingMode;

public class PayableInterfaceTest {
   public static void main(String[] args) {
      // create four-element Payable array
      Payable[] payableObjects = {
         new Invoice("01234", "seat", 2, new BigDecimal("375.00")),
         new Invoice("56789", "tire", 4, new BigDecimal("79.95")),
         new SalariedEmployee("Lorena Silva", new BigDecimal("1200.00")),
         new SalariedEmployee("Hans Olsen", new BigDecimal("800.00"))
      };

      System.out.println(
         "Invoices and Employees processed polymorphically:\n"); 

      // generically process each element in array payableObjects
      for (Payable currentPayable : payableObjects) {
         // output currentPayable and its appropriate payment amount
         System.out.printf("%s:%n%s %npayment due: $%s%n%n", 
            currentPayable.getClass().getName(),
            currentPayable.toString(), // could invoke implicitly
            currentPayable.calculatePayment().setScale(
               2, RoundingMode.HALF_EVEN)); 
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
