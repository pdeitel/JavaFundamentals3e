// Fig. 5.7: SquareDemo.java
// Square method used to demonstrate the  
// method-call stack and activation records.

public class SquareDemo {
   public static void main(String[] args) {
      int y = 10; // value to square (local variable in main)

      int result = square(y); // calculate y squared and store in result
   }

   // returns the square of an integer
   public static int square(int x) {
      return x * x; // calculate square and return result
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
