// Fig. 19.12: SpeechToVTTDemo.java
// Transcribing the audio track from a video and creating closed captions.
import java.nio.file.Files;
import java.nio.file.Path;
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.audio.AudioModel;
import com.openai.models.audio.AudioResponseFormat;
import com.openai.models.audio.transcriptions.Transcription;
import com.openai.models.audio.transcriptions.TranscriptionCreateParams;

public class SpeechToVTTDemo {
   // create an OpenAIClient object
   private final static OpenAIClient client = 
      OpenAIOkHttpClient.fromEnv();

   public static void main(String[] args) throws Exception {
      // get path to resources folder
      Path resourcesPath = Path.of(System.getProperty("user.home"),
         "Documents", "examples", "ch19", "resources");

      // get path to audio file ImplicitClass.m4a
      Path audioPath = resourcesPath.resolve("ImplicitClass.m4a");

      // convert speech to closed captions with OpenAI's whisper-1 model
      System.out.println(
         "Transcribing audio and creating VTT captions file...");
      String captions = speechToVTT(audioPath);
      System.out.printf("CAPTIONS:%n%s%n", captions);

      // output the captions to a .vtt file
      var captionsPath = Path.of(resourcesPath.toString(), 
         "outputs", "ImplicitClass.vtt");
      Files.writeString(captionsPath, captions);
   }
   
   // convert audio track of a video into VTT-formatted transcription 
   // segments and their timestamps for use as closed captions
   public static String speechToVTT(Path audioPath) {
      // configure parameters for an Audio API transcription request that
      // uses whisper-1 to get a VTT-formatted transcription
      var params = TranscriptionCreateParams.builder()
         .file(audioPath)
         .model(AudioModel.WHISPER_1)
         .responseFormat(AudioResponseFormat.VTT)
         .build();

      // initiate the request and wait for the response
      Transcription transcription =
         client.audio().transcriptions().create(params).asTranscription();

      return transcription.text(); // return the transcription's text
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
