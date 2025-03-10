// Fig. 11.5: ReadJSONFile.java
// Reading data from a JSON file using the Jackson open-source library.
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

// record class to represent an account
record Account(int accountNumber, String name, double balance) {}

public class ReadJSONFile {
   public static void main(String[] args) {
      // Path to clients.json in user's Documents folder
      Path filePath = Path.of(System.getProperty("user.home"), 
         "Documents", "clients.json");

      var mapper = new ObjectMapper(); // performs the deserialization

      try {
         // Read accounts from the JSON file
         List<Account> accounts = mapper.readValue(
            filePath.toFile(), new TypeReference<List<Account>>() {});

         // display the accounts 
         System.out.printf(
            "%-10s %-10s %7s%n", "Account", "Name", "Balance");

         for (var account : accounts) {
            System.out.printf("%-10d %-10s %7.2f%n", 
               account.accountNumber(), account.name(), 
               account.balance());
         }
      } 
      catch (IOException e) {
         System.err.printf(
            "Error reading JSON file: %s%n", e.getMessage());
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
