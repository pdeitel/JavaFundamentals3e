// Fig. 9.15: Employee.java
// An Employee "has a" CompensationModel.
import java.math.BigDecimal;

public final class Employee {
   private final String name;
   private CompensationModel model;

   // constructor performs "constructor injection" to initialize  
   // the model reference to a CompensationModel implementation 
   public Employee(String name, CompensationModel model) {
      this.name = name;
      this.model = model;
   }

   // set method performs "property injection" to change the   
   // model to a new CompensationModel implementation 
   public void setCompensationModel(CompensationModel model) {
      this.model = model;
   }

   // return the current CompensationModel 
   public CompensationModel getCompensationModel() {return model;}

   // use the CompensationModel to calculate the Employee's earnings
   public BigDecimal calculateEarnings() {
      return model.calculateEarnings();
   }

   // return String representation of Employee object 
   @Override
   public String toString() {
      return String.format("%s\n%s", name, model.toString());
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
