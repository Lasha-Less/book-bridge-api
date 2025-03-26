package com.lb.book_bridge_api.controller;

import com.lb.book_bridge_api.service.JsonToXmlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book-bridge/transform")
public class JsonToXmlController {

    @Autowired
    private JsonToXmlService jsonToXmlService;

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


}
