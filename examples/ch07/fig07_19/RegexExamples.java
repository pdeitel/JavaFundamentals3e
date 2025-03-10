// Fig. 7.19: RegexExamples.java
// Matching entire Strings to regular expressions.
public class RegexExamples {
   public static void main(String[] args) {
      // fully match a pattern of literal characters
      System.out.println("Matching against: 02215");
      System.out.printf("02215: %b; 51220: %b%n%n",
         "02215".matches("02215"), "51220".matches("02215"));

      // fully match five digits
      System.out.println("Matching against: \\d{5}");
      System.out.printf("02215: %b; 9876: %b%n%n",
         "02215".matches("\\d{5}"), "9876".matches("\\d{5}"));

      // match a word that starts with a capital letter
      System.out.println("Matching against: [A-Z][a-z]*");
      System.out.printf("Angel: %b; tina: %b%n%n",
         "Angel".matches("[A-Z][a-z]*"), "tina".matches("[A-Z][a-z]*"));

      // match any character that's not a lowercase letter
      System.out.println("Matching against: [^a-z]");
      System.out.printf("A: %b; a: %b%n%n",
         "A".matches("[^a-z]"), "a".matches("[^a-z]"));

      // match metacharacters as literals in a custom character class
      System.out.println("Matching against: [*+$]");
      System.out.printf("*: %b; !: %b%n%n",
         "*".matches("[*+$]"), "!".matches("[*+$]"));

      // match a capital letter followed by at least one lowercase letter 
      System.out.println("Matching against: [A-Z][a-z]+");
      System.out.printf("Angel: %b; T: %b%n%n",
         "Angel".matches("[A-Z][a-z]+"), "T".matches("[A-Z][a-z]+"));

      // match zero or one occurrence of a subexpression
      System.out.println("Matching against: labell?ed");
      System.out.printf("labelled: %b; labeled: %b; labellled: %b%n%n",
         "labelled".matches("labell?ed"), "labeled".matches("labell?ed"),
         "labellled".matches("labell?ed"));

      // match n (3) or more occurrences of a subexpression
      System.out.println("Matching against: \\d{3,}");
      System.out.printf("123: %b; 1234567890: %b; 12: %b%n%n",
         "123".matches("\\d{3,}"), "1234567890".matches("\\d{3,}"),
         "12".matches("\\d{3,}"));

      // match n to m inclusive (3-6), occurrences of a subexpression
      System.out.println("Matching against: \\d{3,6}");
      System.out.printf("123: %b; 123456: %b; 1234567: %b; 12: %b%n",
         "123".matches("\\d{3,6}"), "123456".matches("\\d{3,6}"),
         "1234567".matches("\\d{3,6}"), "12".matches("\\d{3,6}"));
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

