// Fig. 10.7: UnnamedVariableDemo.java
// Using _ where a variable name is required.
public class UnnamedVariableDemo {
   public static void main(String[] args) {
      int[] array = {1, 2, 3, 4, 5};

      try {
         int value = array[10]; // index 10 is out of bounds
      }
      catch (ArrayIndexOutOfBoundsException _) {
         System.out.println("Index out of range");
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
