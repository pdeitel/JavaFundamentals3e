// Fig. 19.1: TextSummarizationDemo.java
// Summarizing a transcript as an abstract paragraph and key points.
import deitel.openai.OpenAIUtilities;
import deitel.openai.OpenAIUtilities.Message;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class TextSummarizationDemo {
   public static void main(String[] args) throws Exception {
      // load transcript.txt
      Path transcriptPath = Path.of(System.getProperty("user.home"),
         "Documents", "examples", "ch19", "resources", "transcript.txt");
      String transcript = Files.readString(transcriptPath);

      // get a summary abstract with OpenAI's gpt-4o model
      System.out.println("CREATE A SUMMARY ABSTRACT OF A TRANSCRIPT");
      String summaryAbstract = OpenAIUtilities.chat("gpt-4o",
         List.of(
            new Message("system", """
               Given a transcript of a technical presentation, create a 
               concise, clear summary abstract in paragraph form, written 
               in a direct style that avoids prepositional phrases and 
               uses straightforward sentence structures. Focus on the key 
               points without referring to the speaker. Capture the key 
               ideas, so a person can understand it without reading the 
               full transcript."""),
            new Message("user", transcript)
         )
      );
      System.out.printf("%s%n%n", summaryAbstract);

      // get key points with OpenAI's gpt-4o model
      System.out.println("EXTRACT KEY POINTS FROM A TRANSCRIPT");
      String keyPoints = OpenAIUtilities.chat("gpt-4o",
         List.of(
            new Message("system", """
               Given a transcript of a technical presentation, identify 
               the top 5 key points and present them as a numbered list.  
               For each point, use a concise, direct, clear, and
               straightforward sentence. Avoid prepositional phrases."""),
            new Message("user", transcript)
         )
      );
      System.out.printf("%s%n", keyPoints);
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
