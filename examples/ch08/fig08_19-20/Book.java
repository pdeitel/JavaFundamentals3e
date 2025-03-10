// Fig. 8.19: Book.java
// Declaring an enum type with a constructor and explicit instance fields 
// and accessors for these fields

public enum Book {
   // declare constants of enum type                          
   JHTP("Java How to Program", "2025"),                       
   CHTP("C How to Program", "2020"),                          
   CPP20FP("C++20 for Programmers", "2023"),
   CPPHTP("C++ How to Program", "2024"),                      
   PYCDS("Intro to Python for Computer Science and Data Science", "2020"),
   PYFP("Python for Programmers", "2020");             

   // instance fields 
   private final String title; // book title
   private final String copyrightYear; // copyright year

   // enum constructor
   Book(String title, String copyrightYear) {
      this.title = title;
      this.copyrightYear = copyrightYear;
   } 

   // accessor for field title
   public String getTitle() {
      return title;
   } 

   // accessor for field copyrightYear
   public String getCopyrightYear() {
      return copyrightYear;
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
