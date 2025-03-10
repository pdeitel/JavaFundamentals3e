// Fig. 9.9: PayrollSystemTest.java
// Employee hierarchy test program.
import java.math.BigDecimal;
import java.math.RoundingMode;

public class PayrollSystemTest {
   public static void main(String[] args) {
      // create subclass objects                                          
      var salariedEmployee = new SalariedEmployee("Sierra Dembo", 
         new BigDecimal("1000.00"));       
      var commissionEmployee = new CommissionEmployee("James Davis", 
         new BigDecimal("10000.00"), new BigDecimal("0.06"));

      System.out.println("Employees processed individually:");

      System.out.printf("%s%nearned: $%s%n%n", salariedEmployee, 
         salariedEmployee.calculateEarnings().setScale(
            2, RoundingMode.HALF_EVEN));
      System.out.printf("%s%nearned: $%s%n%n", commissionEmployee, 
         commissionEmployee.calculateEarnings().setScale(
            2, RoundingMode.HALF_EVEN));

      // create and initialize Employee array
      Employee[] employees = {salariedEmployee, commissionEmployee}; 

      System.out.println("Employees processed polymorphically:");

      // generically process each element in array employees
      for (Employee currentEmployee : employees) {
         System.out.println(currentEmployee); // invokes toString

         // determine whether element is a SalariedEmployee
         if (currentEmployee instanceof SalariedEmployee employee) {
            employee.setSalary(
               employee.getSalary().multiply(new BigDecimal("1.10")));

            System.out.printf(
               "new salary with 10%% increase is: $%s%n",              
               employee.getSalary().setScale(2, RoundingMode.HALF_EVEN));
         } 

         System.out.printf("earned: $%s%n%n", 
            currentEmployee.calculateEarnings().setScale(
               2, RoundingMode.HALF_EVEN));
      } 

      // get type name of each object in employees array
      for (int j = 0; j < employees.length; ++j) {      
         System.out.printf("Employee %d is a %s%n", j,  
            employees[j].getClass().getName());         
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
