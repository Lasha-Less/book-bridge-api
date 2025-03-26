package com.lb.book_bridge_api.controller;

import com.lb.book_bridge_api.service.BookSearchService;
import com.lb.book_bridge_api.service.JsonToXmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book-bridge/transform")
public class JsonToXmlController {

    private final BookSearchService bookSearchService;
    private final JsonToXmlService jsonToXmlService;

    public JsonToXmlController(BookSearchService bookSearchService, JsonToXmlService jsonToXmlService) {
        this.bookSearchService = bookSearchService;
        this.jsonToXmlService = jsonToXmlService;
    }

    @PostMapping(value = "/json-to-xml", consumes = "application/json", produces = "application/xml")
    public ResponseEntity<String> convertJsonToXml(@RequestBody String jsonBody) {
        try {
            String xmlOutput = jsonToXmlService.convertJsonToXml(jsonBody);
            return ResponseEntity.ok(xmlOutput);
        } catch (Exception e) {
            // Check if it's likely a client-side JSON issue
            if (e.getMessage() != null && e.getMessage().toLowerCase().contains("unrecognized")) {
                return ResponseEntity.badRequest().body("Invalid JSON format: " + e.getMessage());
            }
            return ResponseEntity.internalServerError().body("Unexpected error: " + e.getMessage());
        }
    }

    @GetMapping(value = "/search-to-xml", produces = "application/xml")
    public ResponseEntity<String> searchAndConvertToXml(
            @RequestParam String title,
            @RequestParam(required = false, defaultValue = "") String authorFirstName,
            @RequestParam(required = false, defaultValue = "") String authorLastName) {
        try {
            String jsonResponse = bookSearchService.fetchBooks(title, authorFirstName, authorLastName);
            String xmlOutput = jsonToXmlService.convertJsonToXml(jsonResponse);
            return ResponseEntity.ok(xmlOutput);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error during transformation: " + e.getMessage());
        }
    }


}
