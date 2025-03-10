// Fig. 7.1: StringConstructors.java
// String class constructors.

public class StringConstructors {
   public static void main(String[] args) {
      char[] charArray = {'b', 'i', 'r', 't', 'h', 'd', 'a', 'y'};
      var s = new String("hello");

      // use String constructors              
      var s1 = new String(); // ""
      var s2 = new String(s); // "hello"
      var s3 = new String(charArray); // "birthday"
      var s4 = new String(charArray, 5, 3); // "day"

      System.out.printf(
         "s1 = %s%ns2 = %s%ns3 = %s%ns4 = %s%n", s1, s2, s3, s4);
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
