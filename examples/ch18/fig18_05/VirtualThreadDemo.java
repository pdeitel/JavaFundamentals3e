// Fig. 18.5: VirtualThreadDemo.java
// Using virtual threads to execute Runnables.
import java.util.concurrent.Executors;

public class VirtualThreadDemo {
   public static void main(String[] args) {
      // PrintTask constructor assigns random sleep time to each
      PrintTask task1 = new PrintTask("task1");
      PrintTask task2 = new PrintTask("task2");
      PrintTask task3 = new PrintTask("task3");
        
      System.out.println("Starting Virtual-Thread-Per-Task Executor\n");

      // The ExecutorService returned by newVirtualThreadPerTaskExecutor
      // runs each task in a virtual thread. The try-with-resources block
      // calls ExecutorService's close method when the block terminates.
      // The program then waits for the virtual threads to complete.
      try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
         // start the three PrintTasks
         executor.execute(task1); // start task1	
         executor.execute(task2); // start task2
         executor.execute(task3); // start task3
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