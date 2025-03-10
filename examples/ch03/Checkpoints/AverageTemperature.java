// Section 3.9, Checkpoint 3: AverageTemperature.java
// Calculate the average of several temperatures
import java.util.Scanner;

public class AverageTemperature {
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);

      int total = 0;
      int counter = 0;

      System.out.print("Enter a temperature or 999 to quit: "); 
      int temperature = input.nextInt();

      while (temperature != 999) {
         total = total + temperature;
         counter = counter + 1;

         System.out.print("Enter a temperature or 999 to quit: "); 
         temperature = input.nextInt();
      }

      if (counter != 0) {
         double average = (double) total / counter;
         System.out.printf("Average temperature is: %.2f%n", average);
      } 
      else {
         System.out.println("No temperatures were entered.");
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