// Fig. 19.7: SpeechToTextDemo.java
// Transcribing audio files to text.
import deitel.openai.OpenAIUtilities;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class SpeechToTextDemo {
   public static void main(String[] args) throws Exception {
      // get path to resources folder
      Path resourcesFolder = Path.of(System.getProperty("user.home"),
         "Documents", "examples", "ch19", "resources");

      // get path to audio file WhatsNewInJavaOverview.m4a
      Path audioPath = Path.of(resourcesFolder.toString(),
         "WhatsNewInJavaOverview.m4a");

      // convert speech to text with OpenAI's whisper-1 model
      System.out.println("Waiting for Transcription...");
      String transcript = OpenAIUtilities.speechToText(
         "whisper-1", audioPath.toString());
      System.out.printf("TRANSCRIPT:%n%s%n", transcript);

      // write the transcription to a file, overwriting it if it exists
      Path transcriptPath = Path.of(resourcesFolder.toString(),
         "outputs", "WhatsNewInJavaOverview.txt");
      Files.writeString(transcriptPath, transcript,
         StandardOpenOption.CREATE,
         StandardOpenOption.TRUNCATE_EXISTING);
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
