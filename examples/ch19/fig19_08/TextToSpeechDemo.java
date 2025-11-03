// Fig. 19.8: TextToSpeechDemo.java
// Synthesizing speech from text.
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.audio.speech.SpeechCreateParams;
import com.openai.models.audio.speech.SpeechCreateParams.Voice;
import com.openai.models.audio.speech.SpeechModel;
import java.nio.file.Files;
import java.nio.file.Path;

public class TextToSpeechDemo {
   // create an OpenAIClient object
   private final static OpenAIClient client = 
      OpenAIOkHttpClient.fromEnv();

   public static void main(String[] args) throws Exception {
      // get path to the resources folder
      Path outputsPath = Path.of(System.getProperty("user.home"),
         "Documents", "examples", "ch19", "resources", "outputs");

      // synthesize speech from text using OpenAI's tts-1-hd model
      System.out.println("TEXT-TO-SPEECH DEMO");
      System.out.println("Synthesizing happy English speech...");
      String english =
         "Today was a beautiful day. Tomorrow looks like bad weather.";
      textToSpeech(english, "Speak in a happy tone.", Voice.ASH,
         outputsPath.resolve("english_happy.mp3"));

      System.out.println("Synthesizing evil English speech...");
      textToSpeech(english, 
         "Speak as an evil supervillain and end with an evil laugh.", 
         Voice.ASH, outputsPath.resolve("english_evil.mp3"));

      System.out.println("Synthesizing Spanish speech...");
      String spanish =
         "Hoy fue un día hermoso. Mañana parece que habrá mal tiempo.";
      textToSpeech(spanish, null, Voice.ASH,
         outputsPath.resolve("spanish.mp3"));

      System.out.println("Synthesizing Japanese speech...");
      String japanese = 
         "今日はとてもいい天気でした。明日は天気が悪そうです。";
      textToSpeech(japanese, null, Voice.ASH,
         outputsPath.resolve("japanese.mp3"));
   }
   
   // synthesize speech from text
   public static void textToSpeech(String text, String guidance, 
      Voice voice, Path path) throws Exception {
         
      // specify the Audio API parameters for a speech request
      var builder = 
         SpeechCreateParams.builder().model(SpeechModel.GPT_4O_MINI_TTS);

      if (guidance != null) { 
         builder = builder.instructions(guidance);
      }
     
      var params = builder.input(text).voice(voice).build();

      // initiate the request and wait for the response
      var response = client.audio().speech().create(params);

      // submit SpeechRequest, wait for audio, then save it to path
      try (var stream = response.body()) {
         Files.write(path, stream.readAllBytes());
         System.out.printf("Wrote audio to %s%n", path);
      }
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
