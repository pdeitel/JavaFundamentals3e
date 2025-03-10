// Fig. 11.7: WeatherApp.java
// Use an OpenWeatherMap web service to get a city's current weather.
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;

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

// record classes for JSON mapping
@JsonIgnoreProperties(ignoreUnknown = true)
record WeatherData(
   Weather[] weather,
   Main main,
   long dt, // date and time
   int timezone,
   String name
) {}

@JsonIgnoreProperties(ignoreUnknown = true)
record Weather(
   String main,
   String description,
   String icon
) {}

@JsonIgnoreProperties(ignoreUnknown = true)
record Main(
   double temp,
   double feels_like,
   int pressure,
   int humidity
) {}

public class WeatherApp {
   public static void main(String[] args) throws Exception {
      // Check for proper command-line arguments
      if (args.length != 1) {
         System.out.println("Usage: WeatherApp <city>");
         System.exit(1);
      }

      String city = args[0]; // city from command-line argument

      // load API key from OPENWEATHERMAP_API_KEY environment variable
      String apiKey = System.getenv("OPENWEATHERMAP_API_KEY");

      // check if API key exists and is set 
      if (apiKey == null || apiKey.isBlank()) {
         System.out.println(
            "OPENWEATHERMAP_API_KEY environment variable is not set.");
         System.exit(1);
      }

      // URL encode the city parameter
      String encodedCity = 
         URLEncoder.encode(city, StandardCharsets.UTF_8);

      // create the API URL as a String
      String apiUrl = String.format(
          "https://api.openweathermap.org/" + 
          "data/2.5/weather?q=%s&units=metric&appid=%s",
          encodedCity, apiKey);

      // use Java's HttpClient to make the request
      HttpClient client = HttpClient.newHttpClient();

      // build the request that HttpClient will use to
      // invoke the OpenWeatherMap web service
      HttpRequest request = 
         HttpRequest.newBuilder()
            .uri(URI.create(apiUrl))
            .GET()
            .build();

      // send the HTTP request and get the response
      HttpResponse<String> response = 
         client.send(request, HttpResponse.BodyHandlers.ofString());

      // check if the request was successful
      if (response.statusCode() != 200) {
         System.out.printf("Error: Received HTTP status %d\n", 
            response.statusCode());
         System.exit(1);
      }

      // parse the JSON response directly into the WeatherData record
      var mapper = new ObjectMapper();
      WeatherData weatherData = 
         mapper.readValue(response.body(), WeatherData.class);

      // convert temperatures to Fahrenheit
      double tempCurrentF = (weatherData.main().temp() * 9 / 5) + 32;
      double feelsLikeF = 
         (weatherData.main().feels_like() * 9 / 5) + 32;

      // generate the icon URL
      String iconName = weatherData.weather()[0].icon();
      String iconUrl = String.format(
         "https://openweathermap.org/img/wn/%s@4x.png", iconName);

      // format the date and time based on the response
      Instant timestamp = Instant.ofEpochSecond(weatherData.dt());
      String formattedDateTime = 
         DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
            .withZone(ZoneId.ofOffset("UTC", 
               ZoneOffset.ofTotalSeconds(weatherData.timezone())))
            .format(timestamp);

      System.out.printf("%s Weather\n", weatherData.name());
      System.out.printf("Date and Time: %s\n", formattedDateTime);
      System.out.printf("Temperature: %.1f C (%.1f F)\n", 
         weatherData.main().temp(), tempCurrentF);
      System.out.printf("Feels like: %.1f C (%.1f F)\n", 
         weatherData.main().feels_like(), feelsLikeF);
      System.out.printf("Pressure: %d hPa\n", 
         weatherData.main().pressure());
      System.out.printf("Humidity: %d%%\n", 
         weatherData.main().humidity());
      System.out.printf("Conditions: %s\n", 
         weatherData.weather()[0].description());
      System.out.printf("Icon: %s\n", iconUrl);
   }
}



