package com.lb.book_bridge_api.controller;

import com.lb.book_bridge_api.service.BookSearchService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/google-books")
public class BookSearchController {

    private final BookSearchService bookSearchService;

    public BookSearchController(BookSearchService bookSearchService) {
        this.bookSearchService = bookSearchService;
    }

    @GetMapping("/search")
    public ResponseEntity<String> searchBooks(
            @RequestParam String title,
            @RequestParam(required = false, defaultValue = "") String authorFirstName,
            @RequestParam(required = false, defaultValue = "") String authorLastName) {

        String jsonResponse = bookSearchService.fetchBooks(title, authorFirstName, authorLastName);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(jsonResponse);
    }


}
