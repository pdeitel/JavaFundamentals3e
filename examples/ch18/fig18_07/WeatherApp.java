// Fig. 18.7: WeatherApp.java
// Fetch a city's weather using OpenWeatherMap API with 
// structured concurrency and scoped values.
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.ScopedValue;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.StructuredTaskScope;
import java.util.stream.Collectors;

// record classes for current weather JSON mapping
@JsonIgnoreProperties(ignoreUnknown = true)
record WeatherData(Weather[] weather, Main main, long dt, 
   int timezone, String name) {}

@JsonIgnoreProperties(ignoreUnknown = true)
record Weather(String main, String description) {}

@JsonIgnoreProperties(ignoreUnknown = true)
record Main(double temp) {}

// record classes for forecast JSON mapping
@JsonIgnoreProperties(ignoreUnknown = true)
record ForecastResponse(List<ForecastEntry> list) {}

@JsonIgnoreProperties(ignoreUnknown = true)
record ForecastEntry(long dt, Main main, Weather[] weather) {}

public class WeatherApp {
   // load OpenWeatherMap API key
   private static final String API_KEY = 
      System.getenv("OPENWEATHERMAP_API_KEY");

   // HttpClient for making web-service requests
   private static final HttpClient HTTP_CLIENT = 
      HttpClient.newHttpClient(); 

   // used to launch each group of requests for a specified city
   private static final ScopedValue<String> CITY = 
      ScopedValue.newInstance();

   public static void main(String[] args) throws Exception {
      // check for proper command-line arguments
      if (args.length == 0) {
         System.out.println("Usage: WeatherApp <city1> <city2> ...");
         System.exit(1);
      }

      // simulate a server doing thread-per-request handling
      try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
         for (String city : args) {
            executor.submit(
               // set the ScopedValue's value then run a task; the task
               // and its subtasks will have access to the ScopedValue
               () -> ScopedValue.where(CITY, city).run(
                  // task: get and print the weather info for the city
                  () -> {
                     try {
                        System.out.printf("%n%nWeather for %s:\n%s\n", 
                           city, getWeather());
                     }
                     catch (Exception ex) {
                        System.err.printf(
                           "Error fetching weather for %s: %s\n", 
                           city, ex.getMessage());
                     }
                  } // end nested lambda expression
               ) // end call to ScopedValue's run method
            ); // end call to the executor object's submit method
         } // end for loop
      } // end try-with-resources statement
   }

   // use structured concurrency to request current weather and forecast
   public static String getWeather() throws Exception {
      // URL encode the city parameter
      String encodedCity = 
         URLEncoder.encode(CITY.get(), StandardCharsets.UTF_8);
      
      // construct API URLs
      String currentWeatherUrl = String.format(
         "https://api.openweathermap.org/data/2.5/weather?q=%s" +
         "&units=metric&appid=%s", encodedCity, API_KEY);
      String forecastUrl = String.format(
         "https://api.openweathermap.org/data/2.5/forecast?q=%s" +
         "&units=metric&appid=%s", encodedCity, API_KEY);
      
      // use StructuredTaskScope to manage concurrent tasks
      try (var scope = new StructuredTaskScope.ShutdownOnFailure()) {
         var currentWeatherTask = scope.fork(
            () -> requestData(currentWeatherUrl, WeatherData.class));
         var forecastTask = scope.fork( 
            () -> requestData(forecastUrl, ForecastResponse.class));
         
         scope.join(); // waits for responses
         scope.throwIfFailed(); // throws an exception if a task fails 

         // tasks completed successfully, retrieve results
         WeatherData weatherData = currentWeatherTask.get();
         ForecastResponse forecastResponse = forecastTask.get();

         // format and return output
         return formatWeatherOutput(weatherData, forecastResponse);
      }
   }
   
   // generic method to retrieve JSON data from OpenWeatherMap API
   private static <T> T requestData(String url, Class<T> type) 
      throws Exception {
      // build the request to the OpenWeatherMap web service
      HttpRequest request = HttpRequest.newBuilder()
         .uri(URI.create(url)).GET().build();
      
      // send the HTTP request and get the response
      HttpResponse<String> response = HTTP_CLIENT.send(request, 
         HttpResponse.BodyHandlers.ofString());
      
      // check if the request was successful; otherwise, throw Exception
      if (response.statusCode() != 200) {
         throw new Exception(String.format(
            "Error: HTTP status %d\n", response.statusCode()));
      }

      // use Jackson library to deserialize the JSON response 
      // into the format specified by type
      return new ObjectMapper().readValue(response.body(), type);
   }
   
   // formats weather output for display
   private static String formatWeatherOutput( 
      WeatherData weatherData, ForecastResponse forecastResponse) {
      
      // current temperature
      double tempC = weatherData.main().temp();
      double tempF = (tempC * 9 / 5) + 32;
      
      // date formatter
      DateTimeFormatter dateFormatter = 
         DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
            .withZone(ZoneId.ofOffset("UTC", 
               ZoneOffset.ofTotalSeconds(weatherData.timezone())));
      
      // 5-day/3-hour forecast of weather descriptions with temperatures
      String forecastInfo = forecastResponse.list().stream().limit(16)
         .map(f -> {
            double tempCForecast = f.main().temp();
            double tempFForecast = (tempCForecast * 9 / 5) + 32;
            return String.format(
               "%s: %s; temp: %.1f C (%.1f F)",
               dateFormatter.format(Instant.ofEpochSecond(f.dt())),
               f.weather()[0].main(), tempCForecast, tempFForecast);
         })
         .collect(Collectors.joining("\n"));
      
      return """
         %s Weather: %s
         Temperature: %.1f C (%.1f F)
         
         Forecast:
         %s""".formatted(weatherData.name(), 
            weatherData.weather()[0].description(), 
            tempC, tempF, forecastInfo);
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