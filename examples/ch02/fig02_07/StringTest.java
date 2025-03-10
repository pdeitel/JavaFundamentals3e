// Fig. 2.7: StringTest.java
// String class test program.
public class StringTest {
   public static void main(String[] args) {
      String s1 = "happy";
      String s2 = " birthday";
      String s3 = ""; // creates an empty string

      // display the Strings and show their lengths
      System.out.printf("s1: \"%s\"; length: %d%n", s1, s1.length());
      System.out.printf("s2: \"%s\"; length: %d%n", s2, s2.length());
      System.out.printf("s3: \"%s\"; length: %d%n%n", s3, s3.length());

      // compare Strings 
      System.out.println("Results of comparing Strings:");
      System.out.printf("s1.equals(\"happy\"): %b%n", s1.equals("happy"));
      System.out.printf("s2.equals(s1): %b%n%n", s2.equals(s1));

      // test String method isEmpty 
      System.out.println("Testing s3.isEmpty():");

      if (s3.isEmpty()) {
         System.out.println("s3 is empty; assigning to s3");
         s3 = s1 + s2; // assign s3 the result of concatenating s1 and s2
         System.out.printf("s3: \"%s\"; length: %d%n%n", s3, s3.length());
      }

      // testing whether Strings start with "ha" or end with "ay" 
      System.out.printf("s1.startsWith(\"ha\"): %b%n", 
         s1.startsWith("ha"));
      System.out.printf("s2.startsWith(\"ha\"): %b%n", 
         s2.startsWith("ha"));
      System.out.printf("s1.endsWith(\"ay\"): %b%n", s1.endsWith("ay"));
      System.out.printf("s2.endsWith(\"ay\"): %b%n", s2.endsWith("ay"));
   } // end method main
} // end class StringTest


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
