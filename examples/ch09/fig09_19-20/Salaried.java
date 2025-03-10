// Fig. 9.16: Salaried.java
// Salaried implements the CompensationModel interface.
import java.math.BigDecimal;

public final class Salaried implements CompensationModel {
   private BigDecimal salary;

   // constructor 
   public Salaried(BigDecimal salary) {
      // if salary is invalid throw exception
      if (salary.compareTo(BigDecimal.ZERO) < 0) { 
         throw new IllegalArgumentException("salary must be >= 0.0");
      }

      this.salary = salary;
   }

   // return the salary
   public BigDecimal getSalary() {return salary;}

   // override CompensationModel abstract method calculateEarnings
   @Override
   public BigDecimal calculateEarnings() {return salary;}

   // override Object method toString  
   @Override
   public String toString() {
      return String.format("salary: $%s", salary);
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
