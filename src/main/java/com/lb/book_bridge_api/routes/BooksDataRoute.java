package com.lb.book_bridge_api.routes;

import com.lb.book_bridge_api.config.GoogleBooksConfig;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class BooksDataRoute extends RouteBuilder {

    private final GoogleBooksConfig config;

    public BooksDataRoute(GoogleBooksConfig config) {
        this.config = config;
    }

    @Override
    public void configure() throws Exception {
        onException(Exception.class)
                .log(LoggingLevel.ERROR, "Error occurred: ${exception.message}")
                .handled(true);

        // 🔹 Fetch Data from Google Books API and return JSON
        from("direct:fetchBookData")
                .routeId("FetchBookDataRoute")
                .errorHandler(defaultErrorHandler()
                        .maximumRedeliveries(3)
                        .redeliveryDelay(2000)
                        .retryAttemptedLogLevel(LoggingLevel.WARN)
                )
                .toD(config.getGoogleBooksApiUrl()
                        + "?q=${header.q}&maxResults=${header.maxResults}&fields=${header.fields}&key=${header.key}")
                .log("Received data: ${body}")  // Log the response
                .convertBodyTo(String.class)
                .transform(body());  // Ensure response is in JSON format
    }

    private String sanitizeFileName(String query) {
        if (query == null || query.isEmpty()) {
            return "books";
        }
        return query.replaceAll("[^a-zA-Z0-9-_]", "_"); // Keep alphanumeric, replace others with "_"
    }

    public String generateFileNameFromQuery(@org.apache.camel.Header("q") String searchQuery) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);

        // Remove "intitle:" and "inauthor:" from searchQuery
        String cleanedQuery = searchQuery
                .replaceAll("intitle:", "")  // Remove "intitle:"
                .replaceAll("inauthor:", "") // Remove "inauthor:"
                .trim(); // Remove leading/trailing spaces

        // Sanitize the filename to remove special characters
        String sanitizedQuery = sanitizeFileName(cleanedQuery);

        return sanitizedQuery + "_" + timestamp + ".json";
    }

    public String generateFileName() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        return "books_" + LocalDateTime.now().format(formatter) + ".json";
    }


}
