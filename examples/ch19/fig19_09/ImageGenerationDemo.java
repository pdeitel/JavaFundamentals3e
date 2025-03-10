// Fig. 19.9: ImageGenerationDemo.java
// Creating images from text prompts.
import deitel.openai.OpenAIUtilities;
import java.nio.file.Path;

public class ImageGenerationDemo {
   public static void main(String[] args) throws Exception {
      // get path to the resources folder
      Path path = Path.of(System.getProperty("user.home"),
         "Documents", "examples", "ch19", "resources", "outputs");

      // generate images with OpenAI image API and dall-e-3 model
      System.out.println("IMAGE-GENERATION DEMO");
      String prompt = """
         Havanese dog as a Japanese anime character
         in neon colors against a black background""";
      System.out.printf("Generating image for:%n%s%n", prompt);
      OpenAIUtilities.image("dall-e-3", prompt,
         Path.of(path.toString(), "HavaneseAnime.png"));

      prompt = "Havanese dog in the style of Vincent Van Gogh";
      System.out.printf("%nGenerating image for:%n%s%n", prompt);
      OpenAIUtilities.image("dall-e-3", prompt,
         Path.of(path.toString(), "HavaneseVanGogh.png"));

      prompt = "Havanese dog in the style of Leonardo DaVinci";
      System.out.printf("%nGenerating image for:%n%s%n", prompt);
      OpenAIUtilities.image("dall-e-3", prompt,
         Path.of(path.toString(), "HavaneseDaVinci.png"));
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
