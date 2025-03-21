package com.lb.book_bridge_api.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GoogleBooksConfig {

    @Value("${google.books.max-results:10}")
    private int maxResults;

    @Value("${google.books.output-directory:data/}")
    private String outputDirectory;

    @Value("${google.books.api.url:https://www.googleapis.com/books/v1/volumes}")
    private String googleBooksApiUrl;

    private static final String API_KEY = System.getenv("GOOGLE_BOOKS_API_KEY");

    // Getters
    public int getMaxResults() {
        return maxResults;
    }

    public String getOutputDirectory() {
        return outputDirectory;
    }

    public String getGoogleBooksApiUrl() {
        return googleBooksApiUrl;
    }

    public String getApiKey() {
        return API_KEY;
    }
}
