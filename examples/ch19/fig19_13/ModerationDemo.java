// Fig. 19.13: ModerationDemo.java
// Using the OpenAI Moderation API to check for offensive content.
import com.openai.client.OpenAIClient;
import com.openai.client.okhttp.OpenAIOkHttpClient;
import com.openai.models.moderations.Moderation;
import com.openai.models.moderations.ModerationCreateParams;
import com.openai.models.moderations.ModerationModel;
import java.util.Scanner;

public class ModerationDemo {
   // create an OpenAIClient object
   private final static OpenAIClient client = 
      OpenAIOkHttpClient.fromEnv();
   
   public static void main(String[] args) {
      var input = new Scanner(System.in);

      System.out.println("Enter prompt (exit to terminate):");
      String prompt = input.nextLine();

      while (!prompt.toLowerCase().equals("exit")) {
         var result = checkText(prompt);

         if (!result.flagged()) {
            System.out.println("Not flagged for offensive content.");
         }
         else {
            displayModerationResults(result);
         }

         System.out.println("\nEnter prompt (exit to terminate):");
         prompt = input.nextLine();
      } 
   }

   // check whether text is potentially harmful or inappropriate  
   public static Moderation checkText(String text) {
      var params = ModerationCreateParams.builder()
         .input(text)
         .model(ModerationModel.OMNI_MODERATION_LATEST)
         .build();

      // initiate the request, wait for the response and return results
      return client.moderations().create(params).results().get(0);
   }

   // extract and display the results
   public static void displayModerationResults(Moderation result) {
      var c = result.categories();
      var s = result.categoryScores();
      System.out.println("\nOffensive content categories & scores:");
      System.out.printf("""
         harassment: %5b %.2f;  threatening: %5b %.2f
               hate: %5b %.2f;  threatening: %5b %.2f
            illicit: %5b %.2f;      violent: %5b %.2f
          self-harm: %5b %.2f; instructions: %5b %.2f; intent: %5b %.2f
             sexual: %5b %.2f;       minors: %5b %.2f
           violence: %5b %.2f;      graphic: %5b %.2f
         """,
         c.harassment(), s.harassment(),
         c.harassmentThreatening(), s.harassmentThreatening(),
         c.hate(), s.hate(),
         c.hateThreatening(), s.hateThreatening(),
         c.illicit(), s.illicit(),
         c.illicitViolent(), s.illicitViolent(),
         c.selfHarm(), s.selfHarm(),
         c.selfHarmInstructions(), s.selfHarmInstructions(),
         c.selfHarmIntent(), s.selfHarmIntent(),
         c.sexual(), s.sexual(),
         c.sexualMinors(), s.sexualMinors(),
         c.violence(), s.violence(),
         c.violenceGraphic(), s.violenceGraphic());         
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
