// Fig. 11.10: TitanicAnalysis.java
// Reading the Titanic dataset from a CSV file, then analyzing it.
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class TitanicAnalysis {
   // record class representing the fields in each dataset row
   @JsonIgnoreProperties(ignoreUnknown = true)
   public record TitanicRecord(
      String survived,
      String sex,
      String age,
      String passengerClass
   ) {}

   public static void main(String[] args) throws Exception {
      var mapper = new CsvMapper(); // reads CSV records

      // specify that Jackson should map each CSV column 
      // to an object based on the column names
      CsvSchema schema = CsvSchema.emptySchema().withHeader(); 

      // create the MappingIterator that knows how to read 
      // the file's records into TitanicRecord objects
      MappingIterator<TitanicRecord> iterator = 
         mapper.readerFor(TitanicRecord.class).with(schema).readValues(
            Path.of("TitanicSurvival.csv").toFile());

      // load dataset into a List of TitanicRecord objects
      List<TitanicRecord> titanic = iterator.readAll();

      // create lists representing each column with the correct type
      var survived = new ArrayList<String>();
      var sex = new ArrayList<String>();
      var age = new ArrayList<Double>();
      var pclass = new ArrayList<Integer>();

      // populate the column lists
      for (TitanicRecord record : titanic) {
         survived.add(record.survived());
         sex.add(record.sex());
         age.add(!record.age().isEmpty() ? 
            Double.parseDouble(record.age()) : Double.NaN);
         pclass.add(
            switch (record.passengerClass()) {
               case "1st" -> 1;
               case "2nd" -> 2;
               case "3rd" -> 3;
               default -> throw new IllegalArgumentException(
                  "Unknown class: " + record.passengerClass());
            }
         );
      }

      // display first 5 rows
      System.out.printf("First five rows:%n%-10s%-8s%-6s%s%n", 
         "survived", "sex", "age", "class");
     
      for (int i = 0; i < 5; ++i) {
         System.out.printf("%-10s%-8s%-6.1f%d%n", 
            survived.get(i), sex.get(i), age.get(i), pclass.get(i));
      }

      // display last 5 rows
      System.out.printf("%nLast five rows:%n%-10s%-8s%-6s%s%n", 
         "survived", "sex", "age", "class");
      int count = titanic.size();
     
      for (int i = count - 5; i < count; ++i) {
         System.out.printf("%-10s%-8s%-6.1f%d%n", 
            survived.get(i), sex.get(i), age.get(i), pclass.get(i));
      }

      // clean the age column (remove NaN)
      var cleanAge = new ArrayList<Double>();
      for (Double value : age) {
         if (!value.isNaN()) {
            cleanAge.add(value);
         }
      }

      // descriptive statistics for cleaned age column
      cleanAge.sort(null); // helps determine min, max and median ages
      int size = cleanAge.size(); 

      // get median age
      double medianAge = (size % 2 == 0) ?
         (cleanAge.get(size / 2 - 1) + cleanAge.get(size / 2)) / 2.0 :
         cleanAge.get(size / 2);

      // calculate average of ages
      double sum = 0.0;
     
      for (double value : cleanAge) {
         sum += value;
      }

      double averageAge = sum / size;

      // display the age statistics
      System.out.printf("%nDescriptive statistics for the age column:%n");
      System.out.printf("Passengers with age data: %d%n", size);
      System.out.printf("Average age: %.2f%n", averageAge);
      System.out.printf("Minimum age: %.2f%n", cleanAge.getFirst());
      System.out.printf("Maximum age: %.2f%n", cleanAge.getLast());
      System.out.printf("Median age: %.2f%n", medianAge);

      // passenger counts by class
      int[] counts = new int[4]; // ignore element 0
     
      for (int value : pclass) {
         ++counts[value];
      }

      System.out.printf(
         "%nPassenger counts by class:%n1st: %d%n2nd: %d%n3rd: %d%n", 
         counts[1], counts[2], counts[3]);

      // summarize counts of people who survived by sex and by class
      int survivorCount = 0;
      int[] survivorsBySex = new int[2];
      int[] survivorsByClass = new int[4]; // ignore element 0

      for (int i = 0; i < survived.size(); ++i) {
         if (survived.get(i).equals("yes")) {
            ++survivorCount;

            if (sex.get(i).equals("female")) {
               ++survivorsBySex[0];
            } 
            else {
               ++survivorsBySex[1];
            }
           
            ++survivorsByClass[pclass.get(i)];
         }
      }

      // calculate and display the survivor statistics
      System.out.printf("%nSurvived count: %d%nDied count: %d%n", 
         survivorCount, survived.size() - survivorCount);
      System.out.printf("Percent who survived: %.2f%%%n", 
         100.0 * survivorCount / survived.size());
      System.out.printf("Female survivor percentage: %.2f%%%n", 
         100.0 * survivorsBySex[0] / survivorCount);
      System.out.printf("Male survivor percentage: %.2f%%%n", 
         100.0 * survivorsBySex[1] / survivorCount);
      System.out.printf("1st class survivor percentage: %.2f%%%n", 
         100.0 * survivorsByClass[1] / survivorCount);
      System.out.printf("2nd class survivor percentage: %.2f%%%n", 
         100.0 * survivorsByClass[2] / survivorCount);
      System.out.printf("3rd class survivor percentage: %.2f%%%n", 
         100.0 * survivorsByClass[3] / survivorCount);
   }
}
