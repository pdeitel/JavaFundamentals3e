// Fig. 19.1: TextSummarizationDemo.java
// Summarizing a transcript as an abstract paragraph and key points.
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import java.nio.file.Files; 
import java.nio.file.Path; 

public class TextSummarizationDemo {
   // create an OpenAIClient object
   private final static OpenAIClient client = 
      OpenAIOkHttpClient.fromEnv();

   public static void main(String[] args) throws Exception {
      // load transcript.txt
      Path transcriptPath = Path.of(System.getProperty("user.home"),
         "Documents", "examples", "ch19", "resources", "transcript.txt");
      String transcript = Files.readString(transcriptPath);

      // get a summary abstract with the OpenAI gpt-5-mini model
      System.out.println("CREATE A SUMMARY ABSTRACT OF A TRANSCRIPT");
      String summaryAbstract = createResponse(ChatModel.GPT_5_MINI, """
         Given a Java technical presentation's transcript, create a
         summary abstract paragraph. Use straightforward sentences.
         Spell language features and method names correctly.
         Do not refer to the speaker.""", transcript);
      System.out.printf("%s%n%n", summaryAbstract);

      // get key points with the OpenAI gpt-5-mini model
      System.out.println("EXTRACT KEY POINTS FROM A TRANSCRIPT");
      String keyPoints = createResponse(ChatModel.GPT_5_MINI, """
         Given a Java technical presentation's transcript,
         return a numbered list of the top 5 key points.""", transcript);
      System.out.printf("%s%n", keyPoints);
   }

   // perform a Responses API request
   public static String createResponse(
      ChatModel model, String instructions, String input) {

      // specify the Responses API parameters
      var params = ResponseCreateParams.builder()
         .model(model)
         .instructions(instructions)
         .input(input)
         .build();

      // initiate the request and wait for the response
      Response response = client.responses().create(params);

      // use lambdas and streams to get the output text
      String outputText = response.output().stream()
         .flatMap(item -> item.message().stream())
         .flatMap(message -> message.content().stream())
         .flatMap(content -> content.outputText().stream())
         .map(output -> output.text())
         .findFirst().orElseThrow();

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
