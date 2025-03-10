// Fig. 9.6: Employee.java
// Employee abstract superclass.
import java.math.BigDecimal;

public abstract class Employee {
   private final String name;

   // constructor
   public Employee(String name) {this.name = name;}

   // return name
   public String getName() {return name;}

   // return String representation of Employee object
   @Override
   public String toString() {return String.format("name: %s", getName());}

   // abstract method must be overridden by concrete subclasses
   public abstract BigDecimal calculateEarnings(); // no implementation
} 



/**************************************************************************
 * (C) Copyright 1992-2025 by Deitel & Associates, Inc. and               *
 * Pearson Education, 2024. All Rights Reserved.                           *
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
