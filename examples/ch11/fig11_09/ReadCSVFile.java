// Fig. 11.9: ReadCSVFile.java
// Reading data from a CSV file using the Jackson open-source library.
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

// record class to represent an account
record Account(int accountNumber, String name, double balance) {}

public class ReadCSVFile {
   public static void main(String[] args) {
      // Path to clients.csv in user's Documents folder
      Path filePath = Path.of(System.getProperty("user.home"), 
         "Documents", "clients.csv");

      var mapper = new CsvMapper(); // reads CSV records

      // specify that Jackson should map each CSV column to
      // an instance variable in the record class Account
      CsvSchema schema = 
         mapper.schemaFor(Account.class) // fields map to Account objects
               .withHeader(); // first line of text is the field names

      // read the CSV file
      try {
         // MappingIterator provides access to records as Account objects
         MappingIterator<Account> iterator = 
            mapper.readerFor(Account.class).with(schema).readValues(
               filePath.toFile());

         List<Account> accounts = iterator.readAll();

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
            "Error reading CSV file: %s%n", e.getMessage());
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
