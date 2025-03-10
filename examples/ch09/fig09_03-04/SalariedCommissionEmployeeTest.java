// Fig. 9.4: SalariedCommissionEmployeeTest.java
// SalariedCommissionEmployee test program.
import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalariedCommissionEmployeeTest {
   public static void main(String[] args) {
      // instantiate SalariedCommissionEmployee object
      var employee = new SalariedCommissionEmployee("James Davis", 
         new BigDecimal("500.00"), new BigDecimal("10000.00"), 
         new BigDecimal("0.04"));
      
      // get SalariedCommissionEmployee data
      System.out.printf("""
         Employee information obtained by get methods:
         name: %s
         salary: $%s
         gross sales: $%s 
         commission rate: %s
         """, employee.getName(), employee.getSalary(), 
         employee.getGrossSales(), employee.getCommissionRate());

      // update gross sales and commission rate
      employee.setGrossSales(new BigDecimal("8000.00")); 
      employee.setCommissionRate(new BigDecimal("0.1")); 
      
      // display updated information and calculate and display earnings
      System.out.printf("%n%s:%n%s%n%nearnings: $%s%n",
         "Updated employee information obtained by toString", employee,
         employee.calculateEarnings().setScale(
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
