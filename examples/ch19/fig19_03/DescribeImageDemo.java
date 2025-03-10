// Fig. 19.3: DescribeImageDemo.java
// Getting accessibility descriptions of images.
import deitel.openai.OpenAIUtilities;
import java.nio.file.Path;

public class DescribeImageDemo {
   public static void main(String[] args) throws Exception {
      // location of resources folder containing sample images
      Path resourcesPath = Path.of(System.getProperty("user.home"),
         "Documents", "examples", "ch19", "resources");

      // get an accessible description of a diagram using gpt-4o
      System.out.println("ACCESSIBLE DESCRIPTION OF A DIAGRAM");
      String diagramDescription =
         OpenAIUtilities.describeImage("gpt-4o", """
            The attached image is a UML activity diagram showing the
            flow of control through a Java 'for' loop with UML 
            notes for additional context.""",
            Path.of(resourcesPath.toString(), "ForLoop.png"));
      System.out.printf("%s%n%n", diagramDescription);

      // get an accessible description of a photo using gpt-4o
      System.out.println("ACCESSIBLE DESCRIPTION OF A PHOTO");
      String imageDescription =
         OpenAIUtilities.describeImage("gpt-4o", """
               Describe in detail the attached photo of a beach scene,
               including every object in the photo.""",
            Path.of(resourcesPath.toString(), "beach.jpg"));
      System.out.printf("%s%n%n", imageDescription);
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
