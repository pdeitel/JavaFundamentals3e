// Fig. 14.8: Employee.java
// Employee class.
public record Employee(
   String firstName, String lastName, double salary, String department) {
   // return Employee's first and last name combined
   public String getName() {
      return String.format("%s %s", firstName(), lastName());
   }

   // return a String containing the Employee's information
   @Override
   public String toString() {
      return String.format("%-8s %-8s %8.2f   %s", 
         firstName(), lastName(), salary(), department());
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
