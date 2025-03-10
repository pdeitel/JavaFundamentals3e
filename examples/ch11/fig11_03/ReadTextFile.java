// Fig. 11.3: ReadTextFile.java
// Reading a text file using a Scanner.
import java.nio.file.Path;
import java.util.Scanner;

public class ReadTextFile {
   public static void main(String[] args) {
      // Path to clients.txt in user's Documents folder
      Path filePath = Path.of(System.getProperty("user.home"), 
         "Documents", "clients.txt");

      // open clients.txt, read its contents and close the file
      try (var input = new Scanner(filePath)) {
         System.out.printf("%-10s%-10s%7s%n", 
            "Account", "Name", "Balance");

         // read record from file
         while (input.hasNext()) { // while there is more to read
            // display record contents                     
            System.out.printf("%-10d%-10s%7.2f%n", 
               input.nextInt(), input.next(), input.nextDouble());
         }
      } 
      catch (Exception e) {
         System.err.printf(
            "Error reading file: %s%n", e.getMessage());
      } 
   } 
} 

/*************************************************************************
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