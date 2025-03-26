package com.lb.book_bridge_api.controller;

import com.lb.book_bridge_api.service.BookSaveService;
import com.lb.book_bridge_api.service.BookSearchService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book-bridge/google-books")
public class BookSearchController {

    private final BookSearchService bookSearchService;
    private final BookSaveService bookSaveService;

    public BookSearchController(BookSearchService bookSearchService, BookSaveService bookSaveService) {
        this.bookSearchService = bookSearchService;
        this.bookSaveService = bookSaveService;
    }

    @GetMapping("/search")
    public ResponseEntity<String> searchBooks(
            @RequestParam String title,
            @RequestParam(required = false, defaultValue = "") String authorFirstName,
            @RequestParam(required = false, defaultValue = "") String authorLastName) {

        if (title == null || title.trim().isEmpty()) {
            return ResponseEntity.badRequest().body("Title parameter is required.");
        }

        String jsonResponse = bookSearchService.fetchBooks(title, authorFirstName, authorLastName);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(jsonResponse);
    }

    @GetMapping("/save")
    public ResponseEntity<String> saveBooks(
            @RequestParam String title,
            @RequestParam(required = false, defaultValue = "") String authorFirstName,
            @RequestParam(required = false, defaultValue = "") String authorLastName) {

        int savedCount = bookSaveService.fetchAndSave(title, authorFirstName, authorLastName);
        return ResponseEntity.ok("✅ Saved " + savedCount + " book items to MongoDB");
    }



}
