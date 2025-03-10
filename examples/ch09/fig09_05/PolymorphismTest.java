// Fig. 9.5: PolymorphismTest.java
// Assigning superclass and subclass references to superclass and
// subclass variables.
import java.math.BigDecimal;
import java.math.RoundingMode;

public class PolymorphismTest {
   public static void main(String[] args) {
      // assign superclass reference to superclass variable 
      var salariedEmployee = new SalariedEmployee("Sierra Dembo", 
         new BigDecimal("1000.00"));       

      // assign subclass reference to subclass variable      
      var salariedCommissionEmployee = new SalariedCommissionEmployee(
         "James Davis", new BigDecimal("500.00"), 
         new BigDecimal("10000.00"), new BigDecimal("0.04"));

      // invoke toString on superclass object using superclass variable
      System.out.printf(""" 
         Call SalariedEmployee's toString and calculateEarnings 
         methods with superclass reference to superclass object:
         %s
         earnings: $%s%n%n""", salariedEmployee,
         salariedEmployee.calculateEarnings().setScale(
            2, RoundingMode.HALF_EVEN));

      // invoke toString on subclass object using subclass variable      
      System.out.printf(""" 
         Call SalariedCommissionEmployee's toString and calculateEarnings
         methods with subclass reference to subclass object:
         %s
         earnings: $%s%n%n""", salariedCommissionEmployee,
         salariedCommissionEmployee.calculateEarnings().setScale(
            2, RoundingMode.HALF_EVEN));

      // invoke toString on subclass object using superclass variable
      SalariedEmployee salariedEmployee2 = salariedCommissionEmployee;
      System.out.printf(""" 
         Call SalariedCommissionEmployee's toString and calculateEarnings
         methods with superclass reference to subclass object:
         %s
         earnings: $%s%n""", salariedEmployee2,
         salariedEmployee2.calculateEarnings().setScale(
            2, RoundingMode.HALF_EVEN));
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
