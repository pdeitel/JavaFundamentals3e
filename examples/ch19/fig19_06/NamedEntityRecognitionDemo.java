// Fig. 19.6: NamedEntityRecognitionDemo.java
// Identifying named entities and obtaining them as structured outputs.
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.responses.ResponseCreateParams;
import com.openai.models.responses.StructuredResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class NamedEntityRecognitionDemo {
   // create an OpenAIClient object
   private final static OpenAIClient client =
      OpenAIOkHttpClient.fromEnv();

   public static void main(String[] args) throws Exception {
      // load web.txt
      Path transcriptPath = Path.of(System.getProperty("user.home"),
         "Documents", "examples", "ch19", "resources", "web.txt");
      String text = Files.readString(transcriptPath);

      // display source text
      System.out.printf("GETTING NAMED ENTITIES FOR:%n%s%n%n", text);

      // create a request to obtain named entities
      NamedEntities results = createStructuredOutputResponse(
         ChatModel.GPT_5_MINI, 
         """
         You are an expert in named entity recognition with the
         OntoNotes Named Entity Tag Set.""", 
         """
         Analyze the supplied text and extract its named entities.
         Return only the requested JSON format. Text to analyze:
         """ + text, 
         NamedEntities.class
      );

      // display named entities
      System.out.println("ENTITIES:");
      results.entities().forEach(entity ->
         System.out.printf("%s: %s%n", entity.text(), entity.tag()));
   }

   // record classes to define the structure of a JSON response
   // containing results of named entity recognition
   public record NamedEntity(String text, String tag) {}
   public record NamedEntities(List<NamedEntity> entities) {}

   // generic method that performs a Responses API request that
   // returns structured output based on the jsonStructure argument
   public static <T> T createStructuredOutputResponse(
      ChatModel model, String instructions, String input,
      Class<T> jsonStructure) throws Exception {

      // specify the Responses API parameters for a structured response
      var params = ResponseCreateParams.builder()
         .model(model)
         .instructions(instructions)
         .input(input)
         .text(jsonStructure) // specifies the structured output format
         .build();

      // initiate the request and wait for the StructuredResponse
      StructuredResponse<T> response = client.responses().create(params);

      // extract the T object and return it
      return response.output().stream()
         .flatMap(item -> item.message().stream())
         .flatMap(message -> message.content().stream())
         .flatMap(content -> content.outputText().stream()) 
         .findFirst().orElseThrow();   
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
