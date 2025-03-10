// Fig. 19.6: NamedEntityRecognitionDemo.java
// Identifying named entities and obtaining them as structured outputs.
import deitel.openai.OpenAIUtilities;
import deitel.openai.OpenAIUtilities.Message;
import deitel.openai.OpenAIUtilities.NamedEntities;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class NamedEntityRecognitionDemo {
   public static void main(String[] args) throws Exception {
      // load web.txt
      Path transcriptPath = Path.of(System.getProperty("user.home"),
         "Documents", "examples", "ch19", "resources", "web.txt");
      String text = Files.readString(transcriptPath);

      // create a request to obtain named entities
      NamedEntities results = OpenAIUtilities.chatWithStructuredOutput(
         "gpt-4o",
         List.of(
            new Message("system", """
               You are an expert in named entity recognition (NER) using
               the OntoNotes 5.0 NER tag set. Analyze the following text
               and return the named entities in the specified structured 
               JSON format. Do not include any additional information in
               your JSON response."""),
            new Message("user", text)
         ),
         OpenAIUtilities.NamedEntities.class
      );

      // display source text
      System.out.printf("SOURCE TEXT:%n%s%n%n", text);

      // display named entities
      System.out.println("ENTITIES:");
      results.entities().forEach(entity ->
         System.out.printf("%s: %s%n", entity.text(), entity.tag()));
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
