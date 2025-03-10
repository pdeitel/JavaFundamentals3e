// Fig. 6.14: ArrayListCollection.java
// Generic ArrayList<E> collection demonstration.
import java.util.ArrayList;

public class ArrayListCollection {
   public static void main(String[] args) {
      // create a new ArrayList of Strings with an initial capacity of 3
      var items = new ArrayList<String>(3);
      System.out.printf(
         "After creating list with initial capacity of 3, size is: %d%n",
         items.size());

      items.add("red"); // append an item to the list      
      items.add(0, "yellow"); // insert "yellow" at index 0

      System.out.print(
         "Display list contents with counter-controlled for loop:"); 

      // display the colors in the list
      for (int i = 0; i < items.size(); ++i) {
         System.out.printf(" %s", items.get(i));
      }

      // display colors using enhanced for in the display method
      display(items,
         "%nDisplay list contents with enhanced for loop:");
      System.out.printf("list size: %d%n", items.size());

      items.add("green"); // add "green" to the end of the list
      items.add("yellow"); // add "yellow" to the end of the list      
      display(items, "\nList with two new elements:"); 
      System.out.println("List has grown beyond initial capacity of 3");
      System.out.printf("list size: %d%n", items.size());

      items.remove("yellow"); // remove the first "yellow"
      display(items, "\nRemove first instance of yellow:"); 
      System.out.printf("list size: %d%n", items.size());

      items.remove(1); // remove item at index 1
      display(items, "\nRemove second list element (green):"); 
      System.out.printf("list size: %d%n", items.size());

      // check if a value is in the List
      System.out.printf("\n\"red\" is %sin the list%n",
         items.contains("red") ? "": "not ");

      // get the index of an item in the list
      System.out.printf("\"red\" is located at index: %d%n", 
         items.indexOf("red"));

      // clear the ArrayList and display its size
      items.clear();
      System.out.printf("\nSize after clear: %s%n", items.size());
   } 

   // display the ArrayList's elements on the console
   public static void display(ArrayList<String> items, String header) {
      System.out.printf(header); // display header

      // display each element in items with enhanced for
      for (String item : items) {
         System.out.printf(" %s", item);
      }

      System.out.println(); 
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
