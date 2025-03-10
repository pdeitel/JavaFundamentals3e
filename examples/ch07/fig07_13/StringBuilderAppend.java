// Fig. 7.13: StringBuilderAppend.java
// StringBuilder append methods.

public class StringBuilderAppend {
   public static void main(String[] args) {
      Object objectRef = "hello"; 
      String string = "goodbye";  
      char[] charArray = {'a', 'b', 'c', 'd', 'e', 'f'};
      boolean booleanValue = true;
      char characterValue = 'Z';
      int integerValue = 7;
      long longValue = 10000000000L;
      float floatValue = 2.5f; 
      double doubleValue = 33.333;

      var lastBuffer = new StringBuilder("last buffer");
      var buffer = new StringBuilder();

      buffer.append(objectRef)                           
            .append(System.lineSeparator())
            .append(string)
            .append(System.lineSeparator())
            .append(charArray)                           
            .append(System.lineSeparator())
            .append(charArray, 0, 3)                     
            .append(System.lineSeparator())
            .append(booleanValue)                        
            .append(System.lineSeparator())
            .append(characterValue)                     
            .append(System.lineSeparator())
            .append(integerValue)                        
            .append(System.lineSeparator())
            .append(longValue)                           
            .append(System.lineSeparator())
            .append(floatValue)                          
            .append(System.lineSeparator())
            .append(doubleValue)                         
            .append(System.lineSeparator())
            .append(lastBuffer);                         

      System.out.printf("buffer contains%n%s%n", buffer.toString());
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
