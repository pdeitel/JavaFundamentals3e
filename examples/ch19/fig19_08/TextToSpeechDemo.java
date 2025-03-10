// Fig. 19.8: TextToSpeechDemo.java
// Synthesizing speech from text.
import deitel.openai.OpenAIUtilities;
import java.nio.file.Path;

public class TextToSpeechDemo {
   public static void main(String[] args) throws Exception {
      // get path to the resources folder
      Path outputPath = Path.of(System.getProperty("user.home"),
         "Documents", "examples", "ch19", "resources", "outputs");

      // synthesize speech from text using OpenAI's tts-1-hd model
      System.out.println("TEXT-TO-SPEECH DEMO");
      System.out.println("Synthesizing English speech...");
      String english =
         "Today was a beautiful day. Tomorrow looks like bad weather.";
      OpenAIUtilities.textToSpeech("tts-1-hd", english, "onyx",
         Path.of(outputPath.toString(), "english.mp3").toString());

      System.out.println("Synthesizing Spanish speech...");
      String spanish =
         "Hoy fue un día hermoso. Mañana parece que habrá mal tiempo.";
      OpenAIUtilities.textToSpeech("tts-1-hd", spanish, "onyx",
         Path.of(outputPath.toString(), "spanish.mp3").toString());

      System.out.println("Synthesizing Japanese speech...");
      String japanese = "今日は美しい日でした。明日は悪天候になりそうです。";
      OpenAIUtilities.textToSpeech("tts-1-hd", japanese, "onyx",
         Path.of(outputPath.toString(), "japanese.mp3").toString());
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
