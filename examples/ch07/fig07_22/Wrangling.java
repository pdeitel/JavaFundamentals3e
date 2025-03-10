// Fig. 7.22: Wrangling.java
// Simple data wrangling tasks to prepare text to train language models.
import java.util.Arrays;

public class Wrangling {
   public static void main(String[] args) {
      String corpus = """
         In C++, an int  on one  machine  might be 16 bits     (2 bytes) 
         of   memory,    on another    32 bits (4 bytes), and on another 
         64 bits (8 bytes).    In Java,   int values are  always 32 bits 
         (4 bytes). The eight  primitive types in Java -- boolean, byte, 
         char, double, float, int, long and short -- are portable across 
         all     computer     platforms      that     support      Java. 
         """;
      System.out.printf("INITIAL CORPUS:%n%s%n", corpus);

      // convert corpus to lowercase
      String lowercased = corpus.toLowerCase();
      System.out.printf("LOWERCASED CORPUS:%n%s%n", lowercased);   

      // remove punctuation
      String noPunctuation = lowercased.replaceAll("\\p{Punct}", "");
      System.out.printf("PUNCTUATION REMOVED:%n%s%n", noPunctuation);   

      // collapsing consecutive whitespace characters to one space
      String spacingNormalized = noPunctuation.replaceAll("\\s+", " ");
      System.out.printf("SPACING NORMALIZED:%n%s%n%n", spacingNormalized);

      // tokenizing the text
      String[] tokenized = spacingNormalized.split(" ");
      System.out.printf("TOKENS:%n%s%n", Arrays.toString(tokenized));
   }
}
