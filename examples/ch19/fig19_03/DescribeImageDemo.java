// Fig. 19.3: DescribeImageDemo.java
// Getting accessibility descriptions of images.
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import com.openai.models.responses.ResponseInputImage;
import com.openai.models.responses.ResponseInputItem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;

public class DescribeImageDemo {
   // create an OpenAIClient object
   private final static OpenAIClient client =
      OpenAIOkHttpClient.fromEnv();

   public static void main(String[] args) throws Exception {
      // location of resources folder containing sample images
      Path resourcesPath = Path.of(System.getProperty("user.home"),
         "Documents", "examples", "ch19", "resources");

      // get an accessible description of a photo using gpt-5-mini
      System.out.println("ACCESSIBLE DESCRIPTION OF A PHOTO");
      String imageDescription = describeImage(ChatModel.GPT_5_MINI,
         "Provide an accessible description of this Aruba sunset photo.",
         resourcesPath.resolve("sunset.jpg"));
      System.out.printf("%s%n%n", imageDescription);

      // get an accessible description of a diagram using gpt-5-mini
      System.out.println("ACCESSIBLE DESCRIPTION OF A DIAGRAM");
      String diagramDescription = describeImage(ChatModel.GPT_5_MINI, """
         Provide an accessible description of this
         UML activity diagram.""", resourcesPath.resolve("ForLoop.png"));
      System.out.printf("%s%n%n", diagramDescription);
   }

   // perform a Responses API request for an image description
   public static String describeImage(
      ChatModel model, String prompt, Path path) throws Exception {

      // create ResponseInputImage containing the Base64-encoded image 
      var inputImage = ResponseInputImage.builder()
         .detail(ResponseInputImage.Detail.AUTO)
         .imageUrl(toDataUrl(path)) // convert image to a "data:" URL
         .build();

      // create ResponseInputItem containing the prompt and inputImage 
      var inputItem = ResponseInputItem.ofMessage(
         ResponseInputItem.Message.builder()
            .role(ResponseInputItem.Message.Role.USER)
            .addInputTextContent(prompt)
            .addContent(inputImage)
            .build());

      // specify the Responses API parameters
      var params = ResponseCreateParams.builder()
         .model(model)
         .instructions("""
            You are an expert at creating accessible image descriptions
            per the World Wide Web Consortium's Web Content
            Accessibility Guidelines (WCAG). Given an image, provide
            alt text and a detailed description for people who are
            blind or have low vision. Identify objects accurately.""")
         .inputOfResponse(List.of(inputItem)) 
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

   // convert an image file to a base64 "data:" URL for image inputs
   private static String toDataUrl(Path path) throws Exception {
      byte[] bytes = Files.readAllBytes(path); 
      String base64Data = Base64.getEncoder().encodeToString(bytes); 
      return String.format("data:%s;base64,%s", 
         Files.probeContentType(path), base64Data);
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
