// Fig. 9.3: SalariedCommissionEmployee.java
// SalariedCommissionEmployee class inherits from SalariedEmployee and
// accesses the superclass's private data via inherited public methods.
import java.math.BigDecimal;

public class SalariedCommissionEmployee extends SalariedEmployee {
   private BigDecimal grossSales; // gross weekly sales
   private BigDecimal commissionRate; // commission percentage

   // four-argument constructor
   public SalariedCommissionEmployee(String name, BigDecimal salary, 
      BigDecimal grossSales, BigDecimal commissionRate) {
      
      super(name, salary); // call superclass constructor

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
   
   // set gross sales
   public void setGrossSales(BigDecimal grossSales) {
      if (grossSales.compareTo(BigDecimal.ZERO) < 0) { 
         throw new IllegalArgumentException("gross sales must be >= 0.0");
      }   

      this.grossSales = grossSales;                
   } 

   // return gross sales
   public BigDecimal getGrossSales() {return grossSales;}

   // set commission rate
   public void setCommissionRate(BigDecimal commissionRate) {
      if (commissionRate.compareTo(BigDecimal.ZERO) <= 0 || 
          commissionRate.compareTo(BigDecimal.ONE) >= 0) { 
         throw new IllegalArgumentException(
            "commission rate must be > 0.0 and < 1.0");
      }   

      this.commissionRate = commissionRate;                
   } 

   // return commission rate
   public BigDecimal getCommissionRate() {return commissionRate;}

   // calculate earnings
   @Override 
   public BigDecimal calculateEarnings() {
      return super.calculateEarnings().add( 
         getGrossSales().multiply(getCommissionRate()));
   }

   // return String representation of SalariedCommissionEmployee
   @Override
   public String toString() {
      return String.format("%s%ngross sales: $%s%ncommission rate: %s", 
         super.toString(), getGrossSales(), getCommissionRate()); 
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
