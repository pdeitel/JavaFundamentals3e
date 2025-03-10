// Fig. 18.10: BlockingBufferTest.java
// Two threads manipulating a blocking buffer that properly 
// implements the producer/consumer relationship.
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

public class BlockingBufferTest {
   public static void main(String[] args) throws InterruptedException {
      // virtual-thread-per-task executor
      try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
         // create BlockingBuffer to store ints       
         final Buffer buffer = new BlockingBuffer();

         // producer task writes 1-10 into buffer
         executor.execute(() -> {
            int sum = 0;

            for (int count = 1; count <= 10; count++) {
               try { 
                  Thread.sleep(ThreadLocalRandom.current().nextInt(3000));  
                  buffer.blockingPut(count); // write value into buffer
                  sum += count; // keep running total of values written 
               } 
               catch (InterruptedException exception) {
                  Thread.currentThread().interrupt(); 
               } 
            } 

            System.out.printf(
               "Producer done: Produced values totaling %d%n", sum);
         });

         // consumer task reads values from buffer
         executor.execute(() -> {
            int sum = 0;

            for (int count = 1; count <= 10; count++) {
               try {
                  Thread.sleep(ThreadLocalRandom.current().nextInt(3000));  
                  sum += buffer.blockingGet(); // read value from buffer
               } 
               catch (InterruptedException exception) {
                  Thread.currentThread().interrupt(); 
               } 
            } 

            System.out.printf(
               "%nConsumer done: Read values totaling %d%n", sum);
         });
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