// Fig. 5.8: AgeCalculator.java
// Using the Date/Time API to calculate how long the user has
// been alive and determine the day of the week they were born.
import java.time.Duration; 
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Scanner;

public class AgeCalculator {
   public static void main(String[] args) {
      var input = new Scanner(System.in);

      // input user's birthday and convert it to a LocalDate
      System.out.print("Enter your birthday (yyyy-MM-dd): ");
      var birthday = LocalDate.parse(input.nextLine());

      // input user's birth time and convert it to a LocalTime
      System.out.print("Enter your birth time (HH:mm): ");
      var birthTime = LocalTime.parse(input.nextLine());

      // combine the birthday and birthTime into a LocalDateTime object
      var birthDateTime = LocalDateTime.of(birthday, birthTime);

      // get the current date and time as a LocalDateTime object
      var now = LocalDateTime.now();

      // show the dates/times
      displayDatesAndTimes(birthDateTime, now); 

      // calculate date/time difference in years, months and days
      var period = Period.between(birthday, now.toLocalDate());
      
      // calculate date/time difference in hours, minutes and seconds
      var duration = Duration.between(birthDateTime, now);
      
      // display day of the week the user was born
      System.out.printf("You were born on a %s%n", 
         birthday.getDayOfWeek().getDisplayName(
            TextStyle.FULL, Locale.getDefault()));

      // display age components
      System.out.printf("""
         You have been alive for %d years, %d months, and %d days
            
         Total months:  %,d
         Total days:    %,d  
         Total hours:   %,d
         Total minutes: %,d
         Total seconds: %,d
         """, 
         period.getYears(), period.getMonths(), period.getDays(), 
         period.toTotalMonths(), 
         birthday.until(now.toLocalDate(), ChronoUnit.DAYS), 
         duration.toHours(), duration.toMinutes(), duration.toSeconds());
   }

   // display birth date/time and current date/time in user's locale
   public static void displayDatesAndTimes(
      LocalDateTime birth, LocalDateTime current) {

      // prepare format to display dates and times for user's locale
      var formatter =
         DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);

      // display formatted date and time
      System.out.printf("%nBirth date/time: %s%n", 
         birth.format(formatter));
      System.out.printf("Current date/time: %s%n%n",
         current.format(formatter));
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
