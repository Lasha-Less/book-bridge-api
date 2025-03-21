package com.lb.book_bridge_api.service;

import com.lb.book_bridge_api.config.GoogleBooksConfig;
import com.lb.book_bridge_api.config.GoogleBooksFields;
import org.apache.camel.ProducerTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class BookSearchService {

   private final ProducerTemplate producerTemplate;
   private final GoogleBooksConfig config;

    public BookSearchService(ProducerTemplate producerTemplate, GoogleBooksConfig config) {
        this.producerTemplate = producerTemplate;
        this.config = config;
    }

    public String fetchBooks(String bookTitle, String authorFirstName, String authorLastName) {
        String searchQuery = "intitle:" + bookTitle.replace(" ", "+") +
                (authorFirstName.isEmpty() ? "" : "+inauthor:" + authorFirstName) +
                (authorLastName.isEmpty() ? "" : "+" + authorLastName);

        Map<String, Object> headers = new HashMap<>();
        headers.put("q", searchQuery);
        headers.put("fields", GoogleBooksFields.FIELDS);
        headers.put("key", config.getApiKey());
        headers.put("maxResults", config.getMaxResults());

        // 🔹 Fetch data from Google Books and return response
        return producerTemplate.requestBodyAndHeaders("direct:fetchBookData", null, headers, String.class);
    }


}
