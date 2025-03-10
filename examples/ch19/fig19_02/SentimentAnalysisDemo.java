// Fig. 19.2: SentimentAnalysisDemo.java
// Analyzing the sentiment of a transcript.
import deitel.openai.OpenAIUtilities;
import deitel.openai.OpenAIUtilities.Message;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class SentimentAnalysisDemo {
   public static void main(String[] args) throws Exception {
      // load transcript.txt
      Path transcriptPath = Path.of(System.getProperty("user.home"),
         "Documents", "examples", "ch19", "resources", "transcript.txt");
      String transcript = Files.readString(transcriptPath);

      // analyze text sentiment with OpenAI's gpt-4o model
      System.out.println("ANALYZE SENTIMENT");
      String sentiment = OpenAIUtilities.chat("gpt-4o",
         List.of(
            new Message("system", """
               You are an expert in sentiment analysis. Analyze the
               following presentation transcript and state whether
               the sentiment is positive, negative, or neutral.
               Explain your analysis."""),
            new Message("user", transcript)
         )
      );
      System.out.printf("%s%n%n", sentiment);
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
