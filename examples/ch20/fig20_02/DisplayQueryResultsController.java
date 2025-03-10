// Fig. 20.2: DisplayQueryResultsController.java
// Controller for the DisplayQueryResults app
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.nio.file.Path;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;

public class DisplayQueryResultsController {
   @FXML private TextArea queryTextArea;
   @FXML private TableView<ObservableList<Object>> tableView;

   // books.db location
   private static final String DATABASE_URL = "jdbc:sqlite:" + 
      Path.of(System.getProperty("user.home"), 
         "Documents", "examples", "ch20", "books.db");

   // default query retrieves all data from authors table
   private static final String DEFAULT_QUERY = "SELECT * FROM authors";

   private Connection connection; 

   // display DEFAULT_QUERY, connect to books.db and execute DEFAULT_QUERY
   @FXML
   public void initialize() {
      queryTextArea.setText(DEFAULT_QUERY);

      try {
         connection = DriverManager.getConnection(DATABASE_URL);
         executeQuery(DEFAULT_QUERY);
      } 
      catch (SQLException sqlException) {
         displayAlert(AlertType.ERROR, "Database Error", 
            sqlException.getMessage());
         System.exit(1); // terminate application
      }
   }

   // query the database and display results in TableView
   @FXML
   void submitQueryButtonPressed(ActionEvent event) {
      executeQuery(queryTextArea.getText());
   }

   // performs the query and updates the ObservableList<Object> 
   // that the TableView uses to populate its rows and columns
   private void executeQuery(String query) {
      try (Statement statement = connection.createStatement();
           ResultSet resultSet = statement.executeQuery(query)) {
         tableView.getColumns().clear(); // remove current content

         // create two-dimensional ObservableList for current ResultSet
         ObservableList<ObservableList<Object>> data = 
            FXCollections.observableArrayList();

         // use the ResultSetMetaData to get the column heads  
         // and set up the TableView's columns
         ResultSetMetaData metaData = resultSet.getMetaData();
         int columnCount = metaData.getColumnCount();

         for (int i = 1; i <= columnCount; i++) {
            // create a new TableColumn for each column in the ResultSet
            var column = new TableColumn<ObservableList<Object>, Object>(
               metaData.getColumnName(i));

            // map the column to the corresponding index in each row
            final int columnIndex = i - 1;
            column.setCellValueFactory(param -> 
               new SimpleObjectProperty<>(
                  param.getValue().get(columnIndex)));

            // autoscale column width to divide space equally
            column.prefWidthProperty().bind(
               tableView.widthProperty().divide(columnCount));

            // add the column to the TableView
            tableView.getColumns().add(column);
         }

         // populate the rows from the ResultSet
         while (resultSet.next()) {
            // create a new ObservableList to represent the row
            ObservableList<Object> row = 
               FXCollections.observableArrayList();

            // populate the row with data from the ResultSet
            for (int i = 1; i <= columnCount; i++) {
               row.add(resultSet.getObject(i));
            }

            // add the row to the data list
            data.add(row);
         }

         // set the TableView's items to the ObservableList data 
         tableView.setItems(data);
      } 
      catch (SQLException sqlException) {
         displayAlert(AlertType.ERROR, "Query Error", 
            sqlException.getMessage());
      }
   }

   // display an Alert dialog
   private void displayAlert(
      AlertType type, String title, String message) {
      Alert alert = new Alert(type);
      alert.setTitle(title);
      alert.setContentText(message);
      alert.showAndWait();
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
