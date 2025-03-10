// Fig. 7.22: RegexMatching.java
// Classes Pattern and Matcher.
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class RegexMatching {
   public static void main(String[] args) {
      // performing a simple match
      String s1 = "Programming is fun";
      System.out.printf("s1: %s%n%n", s1);
      System.out.println("Search anywhere in s1:");

      Pattern pattern1 = Pattern.compile("Programming");
      Matcher matcher1 = pattern1.matcher(s1);
      boolean found1 = matcher1.find();

      Pattern pattern2 = Pattern.compile("fun");
      Matcher matcher2 = pattern2.matcher(s1);
      boolean found2 = matcher2.find();

      Pattern pattern3 = Pattern.compile("fn");
      Matcher matcher3 = pattern3.matcher(s1);
      boolean found3 = matcher3.find();

      System.out.printf("Programming: %b; fun: %b; fn: %b%n%n",
         found1, found2, found3);

      // ignoring case
      String s2 = "GABRIELA ALVAREZ";
      System.out.printf("s2: %s%n%n", s2);
      System.out.println("Case insensitive search for Gabriela in s2:");

      Pattern pattern4 = 
         Pattern.compile("Gabriela", Pattern.CASE_INSENSITIVE);
      Matcher matcher4 = pattern4.matcher(s2);

      if (matcher4.find()) {
         System.out.printf("Gabriela found%n");
         System.out.printf("Matched text: %s%n%n", matcher4.group());
      } 
      else {
         System.out.printf("Gabriela not found%n");
      }

      // finding all matches
      String contact = 
         "Lea Dubois, Home: 555-555-1234, Work: 555-555-4321";
      Pattern phonePattern = Pattern.compile("\\d{3}-\\d{3}-\\d{4}");
      Matcher phoneMatcher = phonePattern.matcher(contact);

      System.out.printf("Finding phone numbers in:%n%s%n", contact);
      while (phoneMatcher.find()) {
         System.out.printf("   %s%n", phoneMatcher.group());
      }
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

