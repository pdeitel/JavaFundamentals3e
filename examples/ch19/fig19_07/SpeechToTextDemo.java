// Fig. 19.7: SpeechToTextDemo.java
// Transcribing audio files to text.
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.audio.AudioModel;
import com.openai.models.audio.transcriptions.Transcription;
import com.openai.models.audio.transcriptions.TranscriptionCreateParams;
import java.nio.file.Files;
import java.nio.file.Path;

public class SpeechToTextDemo {
   // create an OpenAIClient object
   private final static OpenAIClient client = 
      OpenAIOkHttpClient.fromEnv();

   public static void main(String[] args) throws Exception {
      // get path to resources folder
      Path resourcesPath = Path.of(System.getProperty("user.home"),
         "Documents", "examples", "ch19", "resources");

      // get path to audio file 01_01.m4a
      Path audioPath = resourcesPath.resolve("01_01.m4a");

      // convert speech to text with OpenAI's whisper-1 model
      System.out.println("Waiting for Transcription...");
      String transcript = 
         speechToText(AudioModel.GPT_4O_TRANSCRIBE, audioPath);
      System.out.printf("TRANSCRIPT:%n%s%n", transcript);

      // write the transcription to a file, overwriting it if it exists
      Path transcriptPath = 
         Path.of(resourcesPath.toString(), "outputs", "01_01.txt");
      Files.writeString(transcriptPath, transcript);
   }

   // convert audio to text transcription
   public static String speechToText(AudioModel model, Path audioPath) {
      // specify the Audio API parameters for a transcription request
      var params = TranscriptionCreateParams.builder()
         .file(audioPath)
         .model(model)
         .build();

      // initiate the request and wait for the response
      Transcription transcription = 
         client.audio().transcriptions().create(params).asTranscription();
      
      return transcription.text(); // return the transcription
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
