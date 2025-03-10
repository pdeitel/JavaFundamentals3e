// Fig. 18.6: PlatformAndVirtualThreads.java
// Timing large numbers of platform vs. virtual threads.
import java.time.Duration;
import java.time.InstantSource;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.LongStream;

public class PlatformAndVirtualThreads {
   // execute and time threads; sum squares of 1 to number of tasks
   private static void executeAndTimeThreads(int tasks, 
      String threadType, ExecutorService executor) throws Exception {
      var clock = InstantSource.system(); // system clock for timing 

      // stores Futures for all the launched tasks
      var futures = new ArrayList<Future<Long>>(tasks);

      System.out.printf(
         "Executing %d tasks on %s...%n", tasks, threadType);
      var start = clock.instant();

      // auto-closes the ExecutorService at the end of the try block;
      // automatically waits for all tasks to complete
      try (executor) {
         // launch the number of tasks specified by tasks parameter
         LongStream.rangeClosed(1, tasks).forEach(value -> 
            futures.add(executor.submit(() -> {
               Thread.sleep(Duration.ofSeconds(1)); // simulate blocking
               return value * value; 
            }))
         );
      } 

      // sum the results from the Futures
      long sum = 0;
      for (var future : futures) {
         sum += future.get(); 
      }

      var end = clock.instant();

      // display results
      System.out.printf("Execution time: %.2f seconds%n", 
         Duration.between(start, end).toMillis() / 1000.0);
      System.out.printf("Sum of squares 1-%d: %d%n%n", tasks, sum);
   }
   
   public static void main(String[] args) throws Exception {
      // check for proper command-line arguments
      if (args.length != 1) {
         System.out.println("Usage: PlatformAndVirtualThreads <count>");
         System.exit(1);
      }

      // get number of threads to launch
      final int TASKS = Integer.parseInt(args[0]); 

      // execute and time platform threads with fixed thread pool
      executeAndTimeThreads(TASKS,  
         "Platform Threads (fixed pool of 1000 threads)", 
         Executors.newFixedThreadPool(1000));

      // execute and time platform threads with cached thread pool
      executeAndTimeThreads(TASKS, 
         "Platform Threads (cached thread pool)", 
         Executors.newCachedThreadPool());

      // execute and time virtual threads
      executeAndTimeThreads(TASKS, 
         "Virtual Threads", Executors.newVirtualThreadPerTaskExecutor());
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