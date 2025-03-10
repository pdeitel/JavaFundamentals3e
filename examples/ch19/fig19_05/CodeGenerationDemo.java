// Fig. 19.5: CodeGenerationDemo.java
// Generating code for a die-rolling simulation.
import deitel.openai.OpenAIUtilities;
import deitel.openai.OpenAIUtilities.Message;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CodeGenerationDemo {
   public static void main(String[] args) throws Exception {
      // path to store Java source-code that's generated
      Path codePath = Path.of(System.getProperty("user.home"),
         "Documents", "examples", "ch19", "resources", "outputs");

      // generate Java code with OpenAI's gpt-4o model
      System.out.println("GENERATE DIE ROLLING SIMULATION CODE");
      String response = OpenAIUtilities.chat("gpt-4o",
         List.of(
            new Message("system",
               "You are an expert programmer in Java's latest version."),
            new Message("user", """
               Create a Java class named RollDie that simulates rolling
               a die 600,000,000 times as quickly as possible. Summarize 
               the frequencies and display them left-aligned in a  
               two-column format with the column heads "Face" and 
               "Frequency". Avoid loops. In the code, use 3-space indents
               and restrict each code line to 74 characters or fewer.""")
         )
      );
      System.out.printf("%s%n%n", response);

      // use regex to extract the code and write it to disk;
      // the Pattern extracts all the text between the delimiters
      // "```java" and "```"
      Pattern pattern =
         Pattern.compile("```java\\s+(.*?)```", Pattern.DOTALL);
      Matcher matcher = pattern.matcher(response);

      // if there was a match, write it into the file RollDie.java
      if (matcher.find()) {
         Files.writeString(
            Path.of(codePath.toString(), "RollDie.java"),
            matcher.group(1).trim(),
            StandardOpenOption.TRUNCATE_EXISTING);
      }
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
