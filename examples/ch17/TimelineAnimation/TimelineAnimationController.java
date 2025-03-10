// Fig. 17.12: TimelineAnimationController.java
// Bounce a circle around a window using a Timeline animation
import java.util.random.RandomGenerator;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class TimelineAnimationController {
   private final static RandomGenerator random = 
      RandomGenerator.getDefault();
   private int dx = random.nextInt(1, 6);
   private int dy = random.nextInt(1, 6);
   @FXML private Circle c;
   @FXML private Pane pane;

   @FXML
   private void initialize() {
      // define a timeline animation
      var timelineAnimation = new Timeline(
         new KeyFrame(Duration.millis(10), e -> {
            // move the circle by the dx and dy amounts
            c.setLayoutX(c.getLayoutX() + dx);
            c.setLayoutY(c.getLayoutY() + dy);
            Bounds bounds = pane.getBoundsInLocal();

            if (hitRightOrLeftEdge(bounds)) {
               dx *= -1;
            }

            if (hitTopOrBottom(bounds)) {
               dy *= -1;
            }
         }) // end call to KeyFrame constructor
      );

      // indicate that the timeline animation should run indefinitely
      timelineAnimation.setCycleCount(Timeline.INDEFINITE);
      timelineAnimation.play();
   }
   
   // determines whether the circle hit the left or right of the window
   private boolean hitRightOrLeftEdge(Bounds bounds) {
      return (c.getLayoutX() <= (bounds.getMinX() + c.getRadius())) ||
         (c.getLayoutX() >= (bounds.getMaxX() - c.getRadius()));      
   }

   // determines whether the circle hit the top or bottom of the window
   private boolean hitTopOrBottom(Bounds bounds) {
      return (c.getLayoutY() <= (bounds.getMinY() + c.getRadius())) ||
         (c.getLayoutY() >= (bounds.getMaxY() - c.getRadius()));      
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
