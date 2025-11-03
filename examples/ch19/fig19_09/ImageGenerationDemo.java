// Fig. 19.9: ImageGenerationDemo.java
// Creating images from text prompts.
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.images.ImageGenerateParams;
import com.openai.models.images.ImageModel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class ImageGenerationDemo {
   // create an OpenAIClient object
   private final static OpenAIClient client = 
      OpenAIOkHttpClient.fromEnv();
   
   public static void main(String[] args) throws Exception {
      // get path to the resources folder
      Path outputsPath = Path.of(System.getProperty("user.home"),
         "Documents", "examples", "ch19", "resources", "outputs");

      // generate images with OpenAI Image API 
      System.out.println("IMAGE-GENERATION DEMO");
      String prompt = """  
         Havanese dog as a Japanese anime character
         in neon colors against a black background""";
      System.out.printf("Generating image for:%n%s%n", prompt);
      createImage(ImageModel.GPT_IMAGE_1, prompt,
         outputsPath.resolve("HavaneseAnime.png"));

      prompt = "Havanese dog in the style of Vincent van Gogh";
      System.out.printf("%nGenerating image for:%n%s%n", prompt);
      createImage(ImageModel.GPT_IMAGE_1, prompt,
         outputsPath.resolve("HavaneseVanGogh.png"));

      prompt = "Havanese dog in the style of Leonardo da Vinci";
      System.out.printf("%nGenerating image for:%n%s%n", prompt);
      createImage(ImageModel.GPT_IMAGE_1, prompt,
         outputsPath.resolve("HavaneseDaVinci.png"));
   }

   // generate an image
   public static void createImage(
      ImageModel model, String prompt, Path path) throws Exception {
         
      // build an ImageRequest with the specified model and prompt
      var params = ImageGenerateParams.builder()
         .prompt(prompt)
         .model(model)
         .build();

      // initiate the request and wait for the ImagesResponse
      var imagesResponse = client.images().generate(params);

      // get Image containing the base64-encoded data
      var image = imagesResponse.data().orElseThrow().getFirst();

      // get and decode the base64-encoded data, then write to path
      Files.write(path,
         Base64.getDecoder().decode(image.b64Json().orElseThrow()));
      System.out.printf("Image stored in:%n%s%n", path);
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
