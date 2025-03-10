// WildcardTest2.java
// Wildcard test program.
import java.util.Arrays;
import java.util.List;

public class WildcardTest2 {
   public static void main(String[] args) {
      // create and initialize a List<Integer>
      Integer[] integers = {1, 2, 3, 4, 5};
      List<Integer> integerList = Arrays.asList(integers);

      System.out.printf("integerList contains: %s%n", integerList);
      System.out.printf("Total of the elements in integerList: %.0f%n%n",
         sum(integerList));

      // create and initialize a List<Double>
      Double[] doubles = {1.1, 3.3, 5.5};
      List<Double> doubleList =  Arrays.asList(doubles);

      System.out.printf("doubleList contains: %s%n", doubleList);
      System.out.printf("Total of the elements in doubleList: %.1f%n%n", 
         sum(doubleList));

      // create and initialize a List<Number> with mixed numeric types
      Number[] numbers = {1, 2.4, 3, 4.1}; 
      List<Number> numberList = Arrays.asList(numbers);

      System.out.printf("numberList contains: %s%n", numberList);
      System.out.printf("Total of the elements in numberList: %.1f%n", 
         sum(numberList));
   }

   // total the elements; using a wildcard in the List parameter
   public static <T extends Number> double sum(List<T> list) {
      double total = 0; // initialize total

      // calculate sum
      for (T element : list) {
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