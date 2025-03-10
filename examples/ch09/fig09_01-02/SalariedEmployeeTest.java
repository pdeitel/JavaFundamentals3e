// Fig. 9.2: SalariedEmployeeTest.java
// SalariedEmployee class test program.
import java.math.BigDecimal;
import java.math.RoundingMode;

public class SalariedEmployeeTest {
   public static void main(String[] args) {
      // instantiate SalariedEmployee object
      var employee = new SalariedEmployee(
         "Sierra Dembo", new BigDecimal("1000.00"));       
      
      // get SalariedEmployee data
      System.out.println("Employee information obtained by get methods:");
      System.out.printf("name: %s%nsalary: $%s%n", employee.getName(),
         employee.getSalary().setScale(2, RoundingMode.HALF_EVEN));

      employee.setSalary(new BigDecimal("1200.00")); // change salary
      
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
