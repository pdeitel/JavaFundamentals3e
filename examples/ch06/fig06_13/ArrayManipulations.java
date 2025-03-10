// Fig. 6.13: ArrayManipulations.java
// Arrays class methods and System.arraycopy.
import java.util.Arrays;

public class ArrayManipulations {
   public static void main(String[] args) {
      // sort doubles into ascending order
      double[] doubles = {8.4, 9.3, 0.2, 7.9, 3.4};
      Arrays.sort(doubles);
      System.out.printf("doubles: %s%n", Arrays.toString(doubles));

      // fill 10-element array with 7s
      int[] filledInts = new int[10]; 
      Arrays.fill(filledInts, 7);
      System.out.printf("filledInts: %s%n", Arrays.toString(filledInts));

      // copy array ints into array intsCopy
      int[] ints = {1, 2, 3, 4, 5, 6};
      int[] intsCopy = new int[ints.length];
      System.arraycopy(ints, 0, intsCopy, 0, ints.length);
      System.out.printf("ints: %s%n", Arrays.toString(ints));
      System.out.printf("intsCopy: %s%n", Arrays.toString(intsCopy));

      // compare ints and intsCopy for equality
      boolean b = Arrays.equals(ints, intsCopy);
      System.out.printf("%nints %s intsCopy%n", 
          (b ? "equals" : "does not equal"));

      // compare ints and filledInts for equality
      b = Arrays.equals(ints, filledInts);
      System.out.printf("ints %s filledInts%n", 
          (b ? "equals" : "does not equal"));

      // search ints for the value 5
      int location = Arrays.binarySearch(ints, 5);
      
      if (location >= 0) {
         System.out.printf("Found 5 at element %d in ints%n", location); 
      } 
      else {
         System.out.println("5 not found in ints"); 
      } 

      // search intArray for the value 8763
      location = Arrays.binarySearch(ints, 8763);

      if (location >= 0) {
         System.out.printf(
            "Found 8763 at element %d in ints%n", location);
      } 
      else {
         System.out.println("8763 not found in ints"); 
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
