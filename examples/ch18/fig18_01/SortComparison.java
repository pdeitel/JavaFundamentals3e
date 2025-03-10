// Fig. 18.1: SortComparison.java
// Comparing the performance of Arrays methods sort and parallelSort.
import java.time.Duration;
import java.time.InstantSource;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class SortComparison {
   public static void main(String[] args) {
      var clock = InstantSource.system(); // system clock for timing sorts

      // create array of random ints, then copy it; Arrays method 
      // parallelSetAll divides the task of randomly initializing  
      // array1 across available cores
      var array1 = new int[100_000_000];
      Arrays.parallelSetAll(array1, 
         i -> ThreadLocalRandom.current().nextInt());
      var array2 = array1.clone();

      // time the sorting of array1 with Arrays method sort 
      System.out.println("Starting sort");
      var sortStart = clock.instant();
      Arrays.sort(array1);              
      var sortEnd = clock.instant();  

      // display timing results
      double sortTime = Duration.between(sortStart, sortEnd).toMillis();
      System.out.printf("Sort time: %.3f seconds%n%n", sortTime / 1000.0);

      // time the sorting of array2 with Arrays method parallelSort
      System.out.println("Starting parallelSort");
      var parallelSortStart = clock.instant();
      Arrays.parallelSort(array2);              
      var parallelSortEnd = clock.instant();  

      // display timing results
      double parallelSortTime = 
         Duration.between(parallelSortStart, parallelSortEnd).toMillis();
      System.out.printf("Sort time: %.3f seconds%n%n", 
         parallelSortTime / 1000.0);

      // display time difference as a percentage
      String percentage = NumberFormat.getPercentInstance().format( 
         (sortTime - parallelSortTime) / parallelSortTime);
      System.out.printf("sort took %s more time than parallelSort%n", 
         percentage);
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