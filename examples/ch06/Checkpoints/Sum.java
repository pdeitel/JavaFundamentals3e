// Section 6.18, Checkpoint 3: Sum.java
// Totaling command-line arguments.
public class Sum {
   public static void main(String[] args) {
      // check if any command-line arguments were provided
      if (args.length == 0) {
         System.out.println(
            "Provide some integer command-line arguments.");
         return;
      }

      int sum = 0;

      // compute the sum of the command-line arguments
      for (String arg : args) {
         sum += Integer.parseInt(arg);
      }

      System.out.printf("Sum is: %d%n", sum); // display the sum
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
