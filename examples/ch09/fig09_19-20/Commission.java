// Fig. 9.17: Commission.java
// Commission implements the CompensationModel interface.
import java.math.BigDecimal;

public final class Commission implements CompensationModel {
   private BigDecimal grossSales;
   private BigDecimal commissionRate;

   // constructor 
   public Commission(BigDecimal grossSales, BigDecimal commissionRate) {
      // if grossSales is invalid throw exception
      if (grossSales.compareTo(BigDecimal.ZERO) < 0) { 
         throw new IllegalArgumentException("gross sales must be >= 0.0");
      }   

      // if commissionRate is invalid throw exception
      if (commissionRate.compareTo(BigDecimal.ZERO) <= 0 || 
          commissionRate.compareTo(BigDecimal.ONE) >= 0) { 
         throw new IllegalArgumentException(
            "commission rate must be > 0.0 and < 1.0");
      }   

      this.grossSales = grossSales;
      this.commissionRate = commissionRate;
   }

   // return the grossSales
   public BigDecimal getGrossSales() {return grossSales;}

   // return the commissionRate
   public BigDecimal getCommissionRate() {return commissionRate;}

   // override CompensationModel abstract method calculateEarnings
   @Override
   public BigDecimal calculateEarnings() {
      return grossSales.multiply(commissionRate);
   }

   // override Object method toString  
   @Override
   public String toString() {
      return String.format("gross sales: $%s; commission rate: %s", 
         grossSales, commissionRate);
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
