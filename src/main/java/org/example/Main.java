package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
import java.util.Arrays;

public class Main {
  public static void main(String[] args) throws IOException {
    // dateFormats();
    // indexOf();
    // clamp();
    org.openjdk.jmh.Main.main(args);
  }

  static void repeat() {

  }

  static void split() {
    String message = "Do.Not.Run.With...Scissors.";
    System.out.println(message);
    System.out.println(Arrays.stream(message.split("\\.")).toList());
    for (int limit = -1; limit < 10; limit++) {
      System.out.println(Arrays.stream(message.splitWithDelimiters("\\.", limit)).toList());
    }
  }

  static void autocloseable() throws FileNotFoundException {
    HttpClient client = null;
    FileReader fr = null;
    BufferedReader br = null;
    try {
      fr = new FileReader("myfile.txt");
      br = new BufferedReader(fr);
      client = HttpClient.newBuilder().build();
      HttpRequest request = HttpRequest.newBuilder()
        .uri(URI.create("https://www.sonarsource.com"))
        .GET()
        .build();
      client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    } finally {
      if (client != null) {
        client.close();
      }
    }
  }

  static void clamp() {
    Math.clamp(42, 0, 0);
  }

  static void indexOf() {
    String message = "Hello, World!";
    message.indexOf('!', 0, 0);
    message.indexOf('!', 0, message.length()); // valid
    message.indexOf('!', 0, message.length()); // valid
    message.indexOf('!', -1, message.length()); // valid
    // message.indexOf('!', 1, 0); // StringIndexOutOfBoundsException
    // message.indexOf(',', 0, 42_000); // StringIndexOutOfBoundsException
    // message.indexOf(',', 0, message.length() + 1); // StringIndexOutOfBoundsException
  }

  static void dateFormats() {
    LocalDateTime firstDayOfJanuary2016 = LocalDateTime.of(2016, 01, 01, 10, 10);
    System.out.println(firstDayOfJanuary2016);
    System.out.println("DateTimeFormatter.ofPattern(\"Y-w\"): " + DateTimeFormatter.ofPattern("Y-w").format(firstDayOfJanuary2016));
    System.out.println("DateTimeFormatter.ofPattern(\"y-w\"): " + DateTimeFormatter.ofPattern("y-w").format(firstDayOfJanuary2016));
    System.out.println("DateTimeFormatter.ofPattern(\"y-W\"): " + DateTimeFormatter.ofPattern("y-W").format(firstDayOfJanuary2016));
    System.out.println("buildAFormatter: " + buildMeAFormatter().format(firstDayOfJanuary2016));
  }

  static DateTimeFormatter buildMeAFormatter() {
    return new DateTimeFormatterBuilder()
      .appendValue(ChronoField.YEAR, 4) // Noncompliant [[sc=20;ec=36;secondary=+2]] {{Change this year format to use the week-based year instead (or the week format to Chronofield.ALIGNED_WEEK_OF_YEAR).}}
      .appendLiteral('-')
      .appendValue(WeekFields.ISO.weekOfWeekBasedYear(), 2)
      .toFormatter();
  }
}
