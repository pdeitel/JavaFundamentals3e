// Fig. 8.25: Records.java
// Demonstrating an Employee record class.
record Employee(
   String first,
   String last,
   double salary,
   String department) {

   // shorthand constructor for validation
   Employee {
      if (salary < 0.0) {
         throw new IllegalArgumentException("salary must be >= 0.0");
      }
   }
}

public class Records {
   public static void main(String[] args) {
      Employee e1 = new Employee("Prince", "Botha", 5000, "IT");
      Employee e2 = new Employee("Myra", "Singh", 7600, "SALES");
      Employee e3 = new Employee("Myra", "Singh", 7600, "SALES");
      
      System.out.println("Getting Employee string representations:");
      System.out.printf("e1: %s%ne2: %s%ne3: %s%n%n", e1, e2, e3);

      System.out.println("Getting data from e1:");
      System.out.printf("first: %s%n", e1.first());
      System.out.printf("last: %s%n", e1.last());
      System.out.printf("salary: %.2f%n", e1.salary());
      System.out.printf("department: %s%n%n", e1.department());
      
      System.out.println("Comparing Employee objects for equality:");
      System.out.printf("e1.equals(e2): %b%n", e1.equals(e2));
      System.out.printf("e2.equals(e3): %b%n%n", e2.equals(e3));

      System.out.println("Creating Employee with invalid salary:");
      Employee e4 = new Employee("Isabella", "Garcia", -1000, "IT");
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
