// Fig. 13.7: TotalNumbersError.java
// Totaling the numbers in a List<Number>.
import java.util.Arrays;
import java.util.List;

public class TotalNumbersErrors {
   public static void main(String[] args) {
      // create and initialize List<Number> 
      Integer[] numbers = {1, 2, 3, 4}; // ints and doubles
      List<Integer> numberList = Arrays.asList(numbers);

      // display numbersList 
      System.out.printf("numberList contains: %s%n", numberList);

      // total numbersList's elements then display the result
      System.out.printf("Total of the elements in numberList: %.1f%n", 
         sum(numberList));
   }

   // calculate total of List elements
   public static double sum(List<Number> list) {
      double total = 0; // initialize total

      // calculate sum
      for (Number element : list) {     
         total += element.doubleValue();
      }

      return total;
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
