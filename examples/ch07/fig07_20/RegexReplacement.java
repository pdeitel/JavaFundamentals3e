// Fig. 7.20: RegexReplacement.java
// Regular expression replacements.
public class RegexReplacement {
   public static void main(String[] args) {
      // replace tabs with commas
      String s1 = "1\t2\t3\t4";
      System.out.printf("Original string: %s%n", s1);
      System.out.printf("New string with commas replacing tabs: %s%n", 
         s1.replaceAll("\\t", ","));
   }
}


/*
 **************************************************************************
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
 **************************************************************************
*/


