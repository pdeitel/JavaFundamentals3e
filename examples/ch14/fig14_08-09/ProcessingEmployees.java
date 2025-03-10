// Fig. 14.9: ProcessingEmployees.java
// Processing streams of Employee objects.
import java.util.Comparator;
import java.util.List;
import java.util.Map;               
import java.util.function.Function; 
import java.util.function.Predicate;
import java.util.stream.Collectors; 

public class ProcessingEmployees {
   public static void main(String[] args) {

      // initialize List<Employee>
      List<Employee> list = List.of(
         new Employee("James", "Roy", 5000, "IT"),
         new Employee("Zaynab", "Hanna", 7600, "IT"),
         new Employee("Zoe", "Lopez", 3587.5, "Sales"),
         new Employee("Mateo", "Lopez", 4700.77, "Marketing"),
         new Employee("Diego", "Lopez", 3200, "IT"),
         new Employee("Emma", "Bernard", 6200, "Sales"),
         new Employee("Xinyi", "Chen", 4236.4, "Marketing"));

      // display all Employees
      System.out.println("Complete Employee list:");
      list.stream().forEach(System.out::println);
      
      // Predicate that returns true for salaries in the range $4000-$6000
      Predicate<Employee> salaryInRange4to6k =                   
         e -> (e.salary() >= 4000 && e.salary() <= 6000);

      // Display Employees with salaries in the range $4000-$6000
      // sorted into ascending order by salary
      System.out.printf(
         "%nEmployees earning $4000-$6000 per month sorted by salary:%n");
      list.stream()                                         
          .filter(salaryInRange4to6k)                        
          .sorted(Comparator.comparing(Employee::salary))
          .forEach(System.out::println);                    

      // Display first Employee with salary in the range $4000-$6000
      System.out.printf("%nFirst employee who earns $4000-$6000:%n%s%n",
         list.stream()                 
             .filter(salaryInRange4to6k)
             .findFirst()
             .get());

      // Functions for getting first and last names from an Employee
      Function<Employee, String> byFirstName = Employee::firstName;
      Function<Employee, String> byLastName = Employee::lastName;  

      // Comparator for comparing Employees by first name then last name
      Comparator<Employee> lastThenFirst =                           
         Comparator.comparing(byLastName).thenComparing(byFirstName);

      // sort employees by last name, then first name 
      System.out.printf(
         "%nEmployees in ascending order by last name then first:%n");
      list.stream()
          .sorted(lastThenFirst)
          .forEach(System.out::println);

      // sort employees in descending order by last name, then first name
      System.out.printf(
         "%nEmployees in descending order by last name then first:%n");
      list.stream()
          .sorted(lastThenFirst.reversed())
          .forEach(System.out::println);

      // display unique employee last names sorted
      System.out.printf("%nUnique employee last names:%n");
      list.stream()
          .map(Employee::lastName)
          .distinct()                
          .sorted()
          .forEach(System.out::println);

      // display only first and last names
      System.out.printf(
         "%nEmployee names in order by last name then first name:%n"); 
      list.stream()
          .sorted(lastThenFirst)
          .map(Employee::getName)
          .forEach(System.out::println);

      // group Employees by department
      System.out.printf("%nEmployees by department:%n"); 
      Map<String, List<Employee>> groupedByDepartment =               
         list.stream()                                                
             .collect(Collectors.groupingBy(Employee::department));
      groupedByDepartment.forEach(                                    
         (department, employeesInDepartment) -> {                     
            System.out.printf("%n%s%n", department);                  
            employeesInDepartment.forEach(                            
               employee -> System.out.printf("   %s%n", employee));   
         }                                                            
      );                                                              

      // count number of Employees in each department
      System.out.printf("%nCount of Employees by department:%n"); 
      Map<String, Long> employeeCountByDepartment =                 
         list.stream()                                              
             .collect(Collectors.groupingBy(Employee::department,
                Collectors.counting()));                            
      employeeCountByDepartment.forEach(                            
         (department, count) -> System.out.printf(                  
            "%s has %d employee(s)%n", department, count));         

      // sum of Employee salaries with DoubleStream sum method
      System.out.printf(
         "%nSum of Employees' salaries (via sum method): %.2f%n",
         list.stream()
             .mapToDouble(Employee::salary)
             .sum());

      // calculate sum of Employee salaries with Stream reduce method
      System.out.printf(
         "Sum of Employees' salaries (via reduce method): %.2f%n",
         list.stream()
             .mapToDouble(Employee::salary)              
             .reduce(0, (value1, value2) -> value1 + value2));  

      // average of Employee salaries with DoubleStream average method
      System.out.printf("Average of Employees' salaries: %.2f%n",
         list.stream()
             .mapToDouble(Employee::salary)
             .average()
             .getAsDouble());      
   } 
} 
 


 
 /*************************************************************************
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
