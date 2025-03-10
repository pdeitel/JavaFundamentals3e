// Fig. 20.4: PersonQueries.java
// PreparedStatements used by the AddressBook program.
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class PersonQueries {
   // addressbook.db location
   private static final String DATABASE_URL = "jdbc:sqlite:" + 
      Path.of(System.getProperty("user.home"), "Documents", 
         "examples", "ch20", "fig20_03-05", "addressbook.db");

   private Connection connection; // manages connection
   private PreparedStatement selectAllPeople;       
   private PreparedStatement selectPeopleByLastName;
   private PreparedStatement insertNewPerson;
    
   // constructor
   public PersonQueries() {
      try {
         connection = DriverManager.getConnection(DATABASE_URL);

         // create query that selects all entries in the AddressBook 
         selectAllPeople = connection.prepareStatement(             
            "SELECT * FROM addresses ORDER BY last, first");
         
         // create query that selects entries with last names 
         // that begin with the specified characters 
         selectPeopleByLastName = connection.prepareStatement("""
            SELECT * FROM addresses WHERE last LIKE ? 
            ORDER BY last, first""");                  
         
         // create insert operation that adds a new entry to the database
         insertNewPerson = connection.prepareStatement("""
            INSERT INTO addresses (first, last, email, phone) 
            VALUES (?, ?, ?, ?)"""); 
      } 
      catch (SQLException sqlException) {
         sqlException.printStackTrace();
         System.exit(1);
      } 
   } 
   
   // select all of the addresses in the database
   public List<Person> getAllPeople() {
      // executeQuery returns ResultSet containing matching entries
      try (ResultSet resultSet = selectAllPeople.executeQuery()) {
         List<Person> results = new ArrayList<>();
         
         while (resultSet.next()) {
            results.add(new Person(
               resultSet.getInt("id"),
               resultSet.getString("first"),
               resultSet.getString("last"),
               resultSet.getString("email"),
               resultSet.getString("phone")));
         } 

         return results;
      }
      catch (SQLException sqlException) {
         sqlException.printStackTrace();         
      }
      
      return null;
   }
   
   // select person by last name
   public List<Person> getPeopleByLastName(String lastName) {
      try {
         selectPeopleByLastName.setString(1, lastName); // set last name
      }
      catch (SQLException sqlException) {
         sqlException.printStackTrace();
         return null;
      }

      // executeQuery returns ResultSet containing matching entries
      try (ResultSet resultSet = selectPeopleByLastName.executeQuery()) {
         List<Person> results = new ArrayList<>();

         while (resultSet.next()) {
            results.add(new Person(
               resultSet.getInt("id"),
               resultSet.getString("first"),
               resultSet.getString("last"),
               resultSet.getString("email"),
               resultSet.getString("phone")));
         } 

         return results;
      }
      catch (SQLException sqlException) {
         sqlException.printStackTrace();
         return null;
      } 
   }
   
   // add an entry
   public int addPerson(String first, String last, 
      String email, String phone) {
      
      // insert the new entry; returns # of rows updated
      try {
         // set parameters
         insertNewPerson.setString(1, first);
         insertNewPerson.setString(2, last);
         insertNewPerson.setString(3, email);
         insertNewPerson.setString(4, phone);

         return insertNewPerson.executeUpdate();
      }
      catch (SQLException sqlException) {
         sqlException.printStackTrace();
         return 0;
      }
   }
   
   // close the database connection
   public void close() {
      try {
         connection.close();
      } 
      catch (SQLException sqlException) {
         sqlException.printStackTrace();
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

 