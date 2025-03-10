// Fig. 9.1: SalariedEmployee.java
// SalariedEmployee class represents an employee paid a fixed salary.
import java.math.BigDecimal;

public class SalariedEmployee extends Object {
   private final String name;                        
   private BigDecimal salary; // fixed weekly Salary       

   // five-argument constructor                                
   public SalariedEmployee(String name, BigDecimal salary) {
      // implicit call to Object's default constructor occurs here   

      // if salary is invalid throw exception
      if (salary.compareTo(BigDecimal.ZERO) < 0) { 
         throw new IllegalArgumentException("salary must be >= 0.0");
      }   

      this.name = name;                                    
      this.salary = salary;                                    
   } 

   // return first name
   public String getName() {return name;}

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

   // return String representation of CommissionEmployee object         
   @Override // indicates that this method overrides a superclass method
   public String toString() {                                           
      return String.format("name: %s%nsalary: $%s", 
         getName(), getSalary());
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
