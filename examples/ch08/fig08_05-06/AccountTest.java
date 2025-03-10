// Fig. 8.6: AccountTest.java
// Inputting and outputting floating-point numbers with Account objects.
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class AccountTest {
   public static void main(String[] args) {
      var account1 = new Account("Wei Li", new BigDecimal("50.00"));
      var account2 = new Account("Logan Brown", new BigDecimal("-7.53"));

      // display initial balance of each object
      System.out.printf("%s balance: $%s%n", account1.getName(), 
         account1.getBalance().setScale(2, RoundingMode.HALF_EVEN));
      System.out.printf("%s balance: $%s%n%n", account2.getName(), 
         account2.getBalance().setScale(2, RoundingMode.HALF_EVEN));
      
      // create a Scanner to obtain input from the user
      var input = new Scanner(System.in);

      System.out.print("Enter deposit amount for account1: "); // prompt
      var depositAmount = input.nextBigDecimal(); // get input
      System.out.printf("%nadding %s to account1 balance%n%n", 
         depositAmount.setScale(2, RoundingMode.HALF_EVEN));
      account1.deposit(depositAmount); // add to account1 balance

      // display balances
      System.out.printf("%s balance: $%s%n", account1.getName(), 
         account1.getBalance().setScale(2, RoundingMode.HALF_EVEN));
      System.out.printf("%s balance: $%s%n%n", account2.getName(), 
         account2.getBalance().setScale(2, RoundingMode.HALF_EVEN));

      System.out.print("Enter deposit amount for account2: "); // prompt
      depositAmount = input.nextBigDecimal(); // get input
      System.out.printf("%nadding %s to account2 balance%n%n", 
         depositAmount.setScale(2, RoundingMode.HALF_EVEN));
      account2.deposit(depositAmount); // add to account2 balance

      // display balances
      System.out.printf("%s balance: $%s%n", account1.getName(), 
         account1.getBalance().setScale(2, RoundingMode.HALF_EVEN));
      System.out.printf("%s balance: $%s%n%n", account2.getName(), 
         account2.getBalance().setScale(2, RoundingMode.HALF_EVEN));
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
