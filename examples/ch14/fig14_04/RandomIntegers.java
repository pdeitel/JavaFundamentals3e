// Fig. 14.4: RandomIntegers.java
// Shifted and scaled random integers.
import java.util.random.RandomGenerator;
import java.util.stream.Collectors;

public class RandomIntegers {
   public static void main(String[] args) {
      var random = RandomGenerator.getDefault();

      // display 10 random integers on separate lines
      System.out.println("Random numbers on separate lines:");
      random.ints(10, 1, 7)               
            .forEach(System.out::println);

      // display 10 random integers on the same line
      String numbers = 
         random.ints(10, 1, 7)                   
               .mapToObj(String::valueOf)        
               .collect(Collectors.joining(" "));
      System.out.printf("%nRandom numbers on one line: %s%n", numbers);

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
