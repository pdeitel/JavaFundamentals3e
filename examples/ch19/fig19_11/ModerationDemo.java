// Fig. 19.11: ModerationDemo.java
// Using the OpenAI moderation API to check for offensive content.
import deitel.openai.OpenAIUtilities;
import java.util.Scanner;

public class ModerationDemo {
   public static void main(String[] args) {
      var input = new Scanner(System.in);

      System.out.print("Enter prompt (exit to terminate): ");
      String prompt = input.nextLine();

      while (!prompt.toLowerCase().equals("exit")) {
         var result = OpenAIUtilities.checkPrompt(prompt);

         if (!result.getFlagged()) {
            System.out.println(
               "Prompt not flagged for offensive content");
         }
         else {
            var categories = result.getCategories();
            var scores = result.getCategoryScores();

            // display report
            System.out.println("Offensive content categories & scores:");
            System.out.printf("""
               harassment: %b %.2f; threatening: %b %.2f
               hate: %b %.2f; threatening: %b %.2f
               self-harm: %b %.2f; instructions: %b %.2f; intent: %b %.2f
               sexual: %b %.2f; minors: %b %.2f
               violence: %b %.2f; graphic: %b %.2f%n""",
               categories.getHarassment(), scores.getHarassment(),
               categories.getHarassmentThreatening(),
               scores.getHarassmentThreatening(),
               categories.getHate(), scores.getHate(),
               categories.getHateThreatening(),
               scores.getHateThreatening(),
               categories.getSelfHarm(), scores.getSelfHarm(),
               categories.getSelfHarmInstructions(),
               scores.getSelfHarmInstructions(),
               categories.getSelfHarmIntent(), scores.getSelfHarmIntent(),
               categories.getSexual(), scores.getSexual(),
               categories.getSexualMinors(), scores.getSexualMinors(),
               categories.getViolence(), scores.getViolence(),
               categories.getViolencGraphic(),
               scores.getViolencGraphic());
         }

         System.out.print("\nEnter prompt (exit to terminate): ");
         prompt = input.nextLine();
      }
   }
}
