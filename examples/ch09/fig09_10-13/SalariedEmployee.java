// Fig. 9.7: SalariedEmployee.java
// SalariedEmployee concrete class extends abstract class Employee.
import java.math.BigDecimal;

public class SalariedEmployee extends Employee {
   private BigDecimal salary; // fixed weekly Salary       

   // constructor                                
   public SalariedEmployee(String name, BigDecimal salary) {
      super(name); 

      // if salary is invalid throw exception
      if (salary.compareTo(BigDecimal.ZERO) < 0) { 
         throw new IllegalArgumentException("salary must be >= 0.0");
      }   

      this.salary = salary;                                    
   } 

   // set salary amount
   public void setSalary(BigDecimal salary) {
      if (salary.compareTo(BigDecimal.ZERO) < 0) { 
         throw new IllegalArgumentException("salary must be >= 0.0");
      }

      this.salary = salary;
   } 

   // return salary amount
   public BigDecimal getSalary() {return salary;}

   // calculate earnings
   public BigDecimal calculateEarnings() {return getSalary();}

   // return String representation of SalariedEmployee object         
   @Override // indicates that this method overrides a superclass method
   public String toString() {                                           
      return String.format("%s%nsalary: $%s", 
         super.toString(), getSalary());
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
