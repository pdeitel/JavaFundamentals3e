// Fig. 19.4: TextTranslationDemo.java
// Translating text among spoken languages.
import deitel.openai.OpenAIUtilities;

public class TextTranslationDemo {
   public static void main(String[] args) throws Exception {
      // translate text with OpenAI's gpt-4o model
      System.out.println("TRANSLATION DEMO");
      String english =
         "Today was a beautiful day. Tomorrow looks like bad weather.";
      System.out.printf("ORIGINAL: %s%n%n", english);

      System.out.println("Translating English to Spanish...");
      String spanish =
         OpenAIUtilities.translate("gpt-4o", english, "Spanish");
      System.out.printf("SPANISH: %s%n%n", spanish);

      System.out.println("Translating English to Japanese...");
      String japanese =
         OpenAIUtilities.translate("gpt-4o", english, "Japanese");
      System.out.printf("JAPANESE: %s%n%n", japanese);

      System.out.println("Translating Spanish to English...");
      String spanishToEnglish =
         OpenAIUtilities.translate("gpt-4o", spanish, "English");
      System.out.printf(
         "SPANISH TO ENGLISH: %s%n%n", spanishToEnglish);

      System.out.println("Translating Japanese to English...");
      String japaneseToEnglish =
         OpenAIUtilities.translate("gpt-4o", japanese, "English");
      System.out.printf(
         "JAPANESE TO ENGLISH: %s%n", japaneseToEnglish);
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
