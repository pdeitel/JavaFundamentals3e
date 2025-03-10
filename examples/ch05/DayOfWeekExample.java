import java.time.LocalDate;
import java.time.DayOfWeek;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DayOfWeekExample {
    public static void main(String[] args) {
        var scanner = new Scanner(System.in);
        
        // Prompt the user to enter a date
        System.out.print("Enter a date (yyyy-MM-dd): ");
        String inputDate = scanner.nextLine();
        
        // Define a formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        
        // Parse the input date
        LocalDate date = LocalDate.parse(inputDate, formatter);
        
        // Find the day of the week
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        
        // Print the day of the week
        System.out.printf("The date %s falls on a %s.%n", date, dayOfWeek);
    }
}