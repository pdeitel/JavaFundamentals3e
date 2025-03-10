// Fig. 18.2: StreamStatisticsComparison.java
// Comparing performance of sequential and parallel stream operations.
import java.time.Duration;
import java.time.InstantSource;
import java.util.Arrays;
import java.util.LongSummaryStatistics;
import java.util.concurrent.ThreadLocalRandom;

public class StreamStatisticsComparison {
   public static void main(String[] args) {
      var clock = InstantSource.system(); // system clock for timing

      // create array of random long values
      var values = new long[1_000_000_000];
      Arrays.parallelSetAll(values, 
         i -> ThreadLocalRandom.current().nextLong(1, 1001));
      
      // perform calculations separately
      var separateStart = clock.instant();                         
      long count = Arrays.stream(values).count();                    
      long sum = Arrays.stream(values).sum();                        
      long min = Arrays.stream(values).min().getAsLong();            
      long max = Arrays.stream(values).max().getAsLong();            
      double average = Arrays.stream(values).average().getAsDouble();
      var separateEnd = clock.instant();                           

      // display results
      System.out.println("Calculations performed separately");
      System.out.printf("    count: %,d%n", count);
      System.out.printf("      sum: %,d%n", sum);
      System.out.printf("      min: %,d%n", min);
      System.out.printf("      max: %,d%n", max);
      System.out.printf("  average: %f%n", average);
      System.out.printf("Total time in milliseconds: %d%n%n", 
         Duration.between(separateStart, separateEnd).toMillis());

      // time summaryStatistics operation with sequential stream
      System.out.println("Calculating statistics on sequential stream");
      var sequentialStart = clock.instant();                     
      LongSummaryStatistics results1 = 
         Arrays.stream(values).summaryStatistics();
      var sequentialEnd = clock.instant();                       

      // display results
      displayStatistics(results1);
      System.out.printf("Total time in milliseconds: %d%n%n", 
         Duration.between(sequentialStart, sequentialEnd).toMillis());

      // time sum operation with parallel stream
      System.out.println("Calculating statistics on parallel stream");
      var parallelStart = clock.instant();                       
      LongSummaryStatistics results2 = 
         Arrays.stream(values).parallel().summaryStatistics();
      var parallelEnd = clock.instant();                         

      // display results
      displayStatistics(results2);
      System.out.printf("Total time in milliseconds: %d%n%n", 
         Duration.between(parallelStart, parallelEnd).toMillis());
   }

   // display's LongSummaryStatistics values
   private static void displayStatistics(LongSummaryStatistics stats) {
      System.out.println("Statistics");
      System.out.printf("    count: %,d%n", stats.getCount());
      System.out.printf("      sum: %,d%n", stats.getSum());
      System.out.printf("      min: %,d%n", stats.getMin());
      System.out.printf("      max: %,d%n", stats.getMax());
      System.out.printf("  average: %f%n", stats.getAverage());
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