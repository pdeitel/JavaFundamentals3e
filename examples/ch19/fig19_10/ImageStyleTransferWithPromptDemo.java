// Fig. 19.10: ImageStyleTransferWithPromptDemo.java
// Style transfer via the Images API's edit capability 
// and a style-transfer prompt.
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.core.MultipartField;
import com.openai.models.images.ImageEditParams;
import com.openai.models.images.ImageModel;
import com.openai.models.images.ImageEditParams.Image;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class ImageStyleTransferWithPromptDemo {
   // create an OpenAIClient object
   private final static OpenAIClient client = 
      OpenAIOkHttpClient.fromEnv();
   
   public static void main(String[] args) throws Exception {
      // get path to the resources folder
      Path resourcesPath = Path.of(System.getProperty("user.home"),
         "Documents", "examples", "ch19", "resources");

      System.out.println("IMAGE-STYLE-TRANSFER-VIA-PROMPT DEMO");

      String styleTransferPrompt = """
         Restyle the input photo into a vibrant swirling impasto 
         painting inspired by post-impressionist brushwork.  
    
         Medium/technique: thick acrylic paint with bold 
         palette-knife swipes and loaded brush strokes; swirling 
         arcs, rhythmic curves, comma-shaped dabs, and layered 
         ridges that give a tactile sheen (impasto).  
    
         Palette: luminous cobalt and ultramarine blues as the 
         dominant field; strong accents of golden yellow and amber; 
         secondary touches of teal and turquoise; minimal orange and 
         white highlights for contrast.  
    
         Composition: shallow depth, decorative and poster-flat; 
         energetic all-over brushwork that simplifies the subject 
         into flowing, abstracted shapes; swirls and curved strokes
         define contours and fur without precise detail.  
    
         Lighting & finish: very saturated, high contrast, minimal 
         shading; painterly, non-photorealistic.  
    
         Do/Don't: maintain subject recognizability by silhouette and 
         major proportions; no text; no signature; avoid fine line 
         drawing or photoreal textures.""";

      // Paths to the image to style and the output file      
      Path imagePath = resourcesPath.resolve("sunset.jpg");
      Path outputPath = Path.of(
         resourcesPath.toString(), "outputs", "styled_sunset.png");
            
      // restyle the image
      restyleWithImagesAPI(ImageModel.GPT_IMAGE_1, imagePath, outputPath, 
         ImageEditParams.Size._1536X1024, styleTransferPrompt);
   }

   // restyles an image via the Images API's edit capability
   public static void restyleWithImagesAPI(ImageModel model, 
      Path imagePath, Path outputPath, ImageEditParams.Size size, 
      String stylePrompt) throws Exception {

      // configure parameters for the image edit request
      ImageEditParams params = ImageEditParams.builder()
         .model(model)  
         .image(MultipartField.<Image>builder()
            .value(Image.ofInputStream(Files.newInputStream(imagePath)))
            .contentType(Files.probeContentType(imagePath))
            .filename(imagePath.getFileName().toString())
            .build())                                 
         .size(size)
         .prompt(stylePrompt)   
         .build();

      // initiate the request and wait for the ImagesResponse
      var imagesResponse = client.images().edit(params);

      // get Image containing the base64-encoded data
      var image = imagesResponse.data().orElseThrow().getFirst();

      // get and decode the base64-encoded data, then write to path
      Files.write(outputPath,
         Base64.getDecoder().decode(image.b64Json().orElseThrow()));
      System.out.printf("Image stored in:%n%s%n", outputPath);
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
