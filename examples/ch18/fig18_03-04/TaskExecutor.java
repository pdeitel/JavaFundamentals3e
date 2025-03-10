// Fig. 18.4: TaskExecutor.java
// Using an ExecutorService to execute Runnables.
import java.util.concurrent.Executors;

public class TaskExecutor {
   public static void main(String[] args) {
      // create and name each runnable
      var task1 = new PrintTask("task1");
      var task2 = new PrintTask("task2");
      var task3 = new PrintTask("task3");
        
      System.out.println("Starting Executor\n");

      // create ExecutorService for executing tasks; when the 
      // try-with-resources statement completes, the ExecutorService's
      // close method shuts it down, preventing it from accepting new
      // tasks and waiting for existing ones to complete execution
      try (var executorService = Executors.newCachedThreadPool()) {
         // start the three PrintTasks
         executorService.execute(task1); // start task1	
         executorService.execute(task2); // start task2
         executorService.execute(task3); // start task3
      } 

      System.out.println("\nMain ends.");
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