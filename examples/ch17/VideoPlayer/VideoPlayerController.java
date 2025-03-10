// Fig. 17.8: VideoPlayerController.java
// Using Media, MediaPlayer and MediaView to play a video. 
import java.net.URL;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.media.Media;             
import javafx.scene.media.MediaPlayer;       
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.media.MediaView;         
import javafx.util.Duration;                 
import org.controlsfx.dialog.ExceptionDialog;

public class VideoPlayerController {
   @FXML private MediaView mediaView;
   @FXML private Button playPauseButton;
   private MediaPlayer mediaPlayer;
      
   @FXML
   private void initialize() {
      // get URL of the video file
      URL url = VideoPlayerController.class.getResource(
         "A1Launch~medium.mp4");
      
      // create a Media object for the specified URL
      var media = new Media(url.toExternalForm());
      
      // create a MediaPlayer to control Media playback
      mediaPlayer = new MediaPlayer(media);
      
      // specify which MediaPlayer to display in the MediaView
      mediaView.setMediaPlayer(mediaPlayer);

      // set handler to be called when the video completes playing
      mediaPlayer.setOnEndOfMedia(() -> {
         mediaPlayer.seek(Duration.ZERO);
         mediaPlayer.pause();            
      });
 
      // set handler that displays an ExceptionDialog if an error occurs
      mediaPlayer.setOnError(() -> {
         var dialog = new ExceptionDialog(mediaPlayer.getError());
         dialog.showAndWait();                          
      });
      
      // set handler to resize mediaView to window size once ready to play
      mediaPlayer.setOnReady(() -> {
         DoubleProperty width = mediaView.fitWidthProperty();  
         DoubleProperty height = mediaView.fitHeightProperty();
         width.bind(Bindings.selectDouble(                     
            mediaView.sceneProperty(), "width"));              
         height.bind(Bindings.selectDouble(                    
            mediaView.sceneProperty(), "height"));             
      });

      // bind playPauseButton's text to the MediaPlayer's status
      playPauseButton.textProperty().bind(Bindings.when(
         mediaPlayer.statusProperty().isEqualTo(Status.PLAYING))
                    .then("Pause")
                    .otherwise("Play")
      );
   }  
   
   // toggle media playback and the text on the playPauseButton
   @FXML
   private void playPauseButtonPressed(ActionEvent e) {
      Status status = mediaPlayer.getStatus();

      if (status == Status.READY || status == Status.PAUSED) {
         mediaPlayer.play();
      }
      else {         
         mediaPlayer.pause();
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
