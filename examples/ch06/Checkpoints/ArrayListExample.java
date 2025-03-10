// Section 6.20, Checkpoint 3: ArrayListExample.java
// ArrayList with integers.
import java.util.ArrayList;

public class ArrayListExample {
   public static void main(String[] args) {
      var list = new ArrayList<Integer>();

      // add 1-5 to the ArrayList
      for (int i = 1; i <= 5; ++i) {
         list.add(i); // auto-boxes the ints 
      }

      System.out.printf("list contains: %s%n", list); 

      // total the list's elements
      int total = 0;

      for (int value : list) { // auto-unboxes the Integers
         total += value;
      }

      System.out.printf("sum of list's elements: %d%n", total);
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
