// Fig. 12.1: CollectionTest.java
// Collection interface demonstrated via an ArrayList object.
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CollectionTest {
   public static void main(String[] args) {
      // add elements in colors array to colorList
      String[] colors = {"MAGENTA", "RED", "WHITE", "BLUE", "CYAN"};
      List<String> colorList = new ArrayList<String>();

      for (String color : colors) {
         colorList.add(color); // adds color to end of colorList
      }

      // add elements in removeColors array to removeList
      String[] removeColors = {"RED", "WHITE", "BLUE"};
      List<String> removeList = new ArrayList<String>();

      for (String color : removeColors) {
         removeList.add(color);
      }

      // output colorList contents
      System.out.println("ArrayList: ");

      for (int count = 0; count < colorList.size(); ++count) {
         System.out.printf("%s ", colorList.get(count));
      }

      // remove from colorList the colors contained in removeList
      removeColors(colorList, removeList);

      // output colorList contents
      System.out.printf("%n%nArrayList after calling removeColors:%n");

      for (String color : colorList) {
         System.out.printf("%s ", color);
      }
   } 

   // remove colors specified in itemsToRemove from allItems
   private static void removeColors(Collection<String> allItems, 
      Collection<String> itemsToRemove) {
      
      Iterator<String> iterator = allItems.iterator(); // get iterator

      // loop while collection has items
      while (iterator.hasNext()) {
         if (itemsToRemove.contains(iterator.next())) {   
            iterator.remove(); // remove current element
         }
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
