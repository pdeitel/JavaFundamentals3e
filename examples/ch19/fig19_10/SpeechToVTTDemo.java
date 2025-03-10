// Fig. 19.10: SpeechToVTTDemo.java
// Transcribing the audio track from a video and creating closed captions.
import deitel.openai.OpenAIUtilities;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class SpeechToVTTDemo {
   public static void main(String[] args) throws Exception {
      // get path to resources folder
      Path resourcesFolder = Path.of(System.getProperty("user.home"),
         "Documents", "examples", "ch19", "resources");

      // get path to audio file ImplicitClass.m4a
      Path audioPath =
         Path.of(resourcesFolder.toString(), "ImplicitClass.m4a");

      // convert speech to closed captions with OpenAI's whisper-1 model
      System.out.println(
         "Transcribing audio and creating VTT captions file...");
      String captions =
         OpenAIUtilities.speechToVTT("whisper-1", audioPath);
      System.out.printf("CAPTIONS:%n%s%n", captions);

      // output the captions to a .vtt file
      var captionsPath = Path.of(resourcesFolder.toString(),
         "outputs", "ImplicitClass.vtt");
      Files.writeString(captionsPath, captions.trim(),
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
