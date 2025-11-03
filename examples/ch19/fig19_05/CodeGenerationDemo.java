// Fig. 19.5: CodeGenerationDemo.java
// Generating code for a die-rolling simulation.
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import java.nio.file.Files;
import java.nio.file.Path;

public class CodeGenerationDemo {
   // create an OpenAIClient object
   private final static OpenAIClient client = 
      OpenAIOkHttpClient.fromEnv();

   public static void main(String[] args) throws Exception {
      // path to store Java source-code that's generated
      Path outputsPath = Path.of(System.getProperty("user.home"),
         "Documents", "examples", "ch19", "resources", "outputs");

      // generate Java code with OpenAI's gpt-5 model
      System.out.println("GENERATE DIE ROLLING SIMULATION CODE");
      String response = createResponse(ChatModel.GPT_5,
         "You are an expert programmer in Java's latest version.", 
         """
         Create a Java class named RollDie that simulates rolling a die 
         600,000,000 times using all available CPU cores. Avoid loops. 
         Summarize the frequencies and nicely format them right-aligned  
         under the column heads "Face" and "Frequency". Return only the 
         code with no markdown formatting. Use three-space indents and 
         a maximum code line length of 74 characters.""");
      System.out.printf("%s%n%n", response);

      // write the code into RollDie.java
      Files.writeString(outputsPath.resolve("RollDie.java"), response);
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
