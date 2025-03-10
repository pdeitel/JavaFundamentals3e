// Fig. 7.16: StaticCharMethods2.java
// Character class static conversion methods.
import java.util.Scanner;

public class StaticCharMethods2 {
   public static void main(String[] args) {
      var scanner = new Scanner(System.in);

      // get radix
      System.out.print("Please enter a radix: ");
      int radix = scanner.nextInt();

      // get user choice
      System.out.println("""
         Please choose one:
            1 -- Convert digit to character
            2 -- Convert character to digit""");
      System.out.print(": ");
      int choice = scanner.nextInt(); 

      // process request
      switch (choice) {
         case 1 -> { // convert digit to character
            System.out.print("Enter a digit: ");
            int digit = scanner.nextInt();
            System.out.printf("Convert digit to character: %s%n",
               Character.forDigit(digit, radix));
         }
         case 2 -> { // convert character to digit
            System.out.print("Enter a character: ");
            char character = scanner.next().charAt(0);
            System.out.printf("Convert character to digit: %s%n",
               Character.digit(character, radix));
         }
      };
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
