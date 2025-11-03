// Fig. 19.11: ImageToImageStyleTransferDemo.java
// Image-to-Image style transfer via the Responses API.
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.ChatModel;
import com.openai.models.responses.Response;
import com.openai.models.responses.ResponseCreateParams;
import com.openai.models.responses.ResponseInputImage;
import com.openai.models.responses.ResponseInputItem;
import com.openai.models.responses.Tool.ImageGeneration;
import com.openai.models.responses.Tool.ImageGeneration.Size;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;
import java.util.List;

public class ImageToImageStyleTransferDemo {
   // create an OpenAIClient object
   private final static OpenAIClient client = 
      OpenAIOkHttpClient.fromEnv();
   
   public static void main(String[] args) throws Exception {
      // get path to the resources folder
      Path resourcesPath = Path.of(System.getProperty("user.home"),
         "Documents", "examples", "ch19", "resources");

      System.out.println("IMAGE-TO-IMAGE-STYLE-TRANSFER DEMO");

      // Paths to the image to style and the output file      
      Path toImagePath = resourcesPath.resolve("sunset.jpg");
      Path fromImagePath = resourcesPath.resolve("style1.jpg");
      Path outputPath = Path.of(resourcesPath.toString(), "outputs", 
         "styled_sunset_from_style1_image.png");
            
      // restyle the image
      restyleWithResponsesAPI(ChatModel.GPT_5_MINI, toImagePath, 
         fromImagePath, outputPath, Size._1536X1024, null);
   }

   // restyles an image using another image's style via the Responses API
   public static void restyleWithResponsesAPI(ChatModel model,
      Path toImagePath, Path fromImagePath, Path outputPath,
      Size size, String moreInfo) throws Exception {

      // form the prompt
      var prompt = """
         Apply the style of the second image to the first.  
         Keep the subject's identity and layout. """;
      
      if (moreInfo != null && !moreInfo.isEmpty()) {
         prompt += "Additional details: " + moreInfo;
      }
      
      // get ResponseInputImages for ResponseInputItem
      ResponseInputImage toImage = imageFromPath(toImagePath);
      ResponseInputImage fromImage = imageFromPath(fromImagePath);

      // create ResponseInputItem containing the prompt and images 
      var inputItem = ResponseInputItem.ofMessage(
         ResponseInputItem.Message.builder()
            .role(ResponseInputItem.Message.Role.USER)
            .addInputTextContent(prompt)
            .addContent(toImage)
            .addContent(fromImage)
            .build());

      // configure parameters for a multimodal Responses API request   
      // consisting of a text prompt and two images
      ResponseCreateParams params = ResponseCreateParams.builder()
         .model(model)
         .addTool(ImageGeneration.builder().size(size).build())
         .inputOfResponse(List.of(inputItem)) 
         .build();

      // initiate the request and wait for the response
      Response response = client.responses().create(params);

      // use lambdas and streams to get the restyled image
      var image = response.output().stream()
         .filter(item -> item.isImageGenerationCall())
         .map(item -> item.asImageGenerationCall())
         .findFirst().orElseThrow();

      // get and decode the base64-encoded data, then write to outputPath
      Files.write(outputPath,
         Base64.getDecoder().decode(image.result().orElseThrow()));
      System.out.printf("Image stored in:%n%s%n", outputPath);
   }

   // get a ResponseInputImage for the image at path
   private static ResponseInputImage imageFromPath(Path path) 
      throws Exception {
      
      byte[] bytes = Files.readAllBytes(path); 
      String b64Data = Base64.getEncoder().encodeToString(bytes); 
      var dataURL = String.format(
         "data:%s;base64,%s", Files.probeContentType(path), b64Data);

      // create ResponseInputImage containing the Base64-encoded image 
      return ResponseInputImage.builder()
         .detail(ResponseInputImage.Detail.AUTO)
         .imageUrl(dataURL)
         .build();
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
