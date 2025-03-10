// CSSTransition.java
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CSSTransition extends Application {
   @Override
   public void start(Stage stage) throws Exception {
      Parent root = 
         FXMLLoader.load(getClass().getResource("CSSTransition.fxml"));
        
      Scene scene = new Scene(root);
      stage.setTitle("CSSTransition");
      stage.setScene(scene);
      stage.show();
   }
   
   public static void main(String[] args) {
      launch(args);
   }
}
