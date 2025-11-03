// Fig. 19.4: TextTranslationDemo.java
// Translating text among spoken languages with OpenAI's gpt-5-mini model.
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;

public class TextTranslationDemo {
   // create an OpenAIClient object
   private final static OpenAIClient client =
      OpenAIOkHttpClient.fromEnv();

   public static void main(String[] args) throws Exception {
      System.out.println("TRANSLATION DEMO");
      String english =
         "Today was a beautiful day. Tomorrow looks like bad weather.";
      System.out.printf("ORIGINAL: %s%n%n", english);

      System.out.println("Translating English to Spanish...");
      String spanish = 
         translate(ChatModel.GPT_5, english, "Spanish");
      System.out.printf("SPANISH: %s%n%n", spanish);

      System.out.println("Translating English to Japanese...");
      String japanese = 
         translate(ChatModel.GPT_5, english, "Japanese");
      System.out.printf("JAPANESE: %s%n%n", japanese);

      System.out.println("Translating Spanish to English...");
      System.out.printf("SPANISH TO ENGLISH: %s%n%n",
         translate(ChatModel.GPT_5, spanish, "English"));

      System.out.println("Translating Japanese to English...");
      System.out.printf("JAPANESE TO ENGLISH: %s%n",
         translate(ChatModel.GPT_5, japanese, "English"));
   }

   // perform a Responses API request for translation
   public static String translate(
      ChatModel model, String textToTranslate, String language) {

      // specify the Responses API parameters
      var params = ResponseCreateParams.builder()
         .model(model)
         .instructions("Translate input to " + language)
         .input(textToTranslate)
         .build();

      // initiate the request and wait for the response
      Response response = client.responses().create(params);

      // use lambdas and streams to get the output text
      String outputText = response.output().stream()
         .flatMap(item -> item.message().stream())
         .flatMap(message -> message.content().stream())
         .flatMap(content -> content.outputText().stream())
         .map(output -> output.text())
         .findFirst().get();

      return outputText;
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
