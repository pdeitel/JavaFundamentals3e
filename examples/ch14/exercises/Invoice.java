// Invoice.java
public record Invoice(String partNumber, String partDescription, 
   int quantity, double pricePerItem) {

   // custom constructor with validation
   public Invoice {
      if (quantity < 0) { // validate quantity
         throw new IllegalArgumentException("Quantity must be >= 0");
      }

      if (pricePerItem < 0.0) { // validate pricePerItem
         throw new IllegalArgumentException("Price per item must be >= 0");
      }
   }

   // return String representation of Invoice object
   @Override
   public String toString() {
      return String.format("%s: %n%s: %s (%s) %n%s: %d %n%s: $%,.2f", 
         "invoice", "part number", partNumber(), partDescription(), 
         "quantity", quantity(), "price per item", pricePerItem());
   } 

   // return amount of this Invoice
   public double getInvoiceAmount() {                                                                   
      return quantity() * pricePerItem(); // calculate total cost
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
