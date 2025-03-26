package com.lb.book_bridge_api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lb.book_bridge_api.model.BookItem;
import com.lb.book_bridge_api.model.BookResponse;
import com.lb.book_bridge_api.repo.BookItemRepo;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
public class BookSaveService {

    private final BookSearchService bookSearchService;
    private final BookItemRepo bookItemRepo;
    private final ObjectMapper objectMapper;
    private final MongoTemplate mongoTemplate;

    public BookSaveService(
            BookSearchService bookSearchService,
            BookItemRepo bookItemRepo,
            ObjectMapper objectMapper,
            MongoTemplate mongoTemplate) {
        this.bookSearchService = bookSearchService;
        this.bookItemRepo = bookItemRepo;
        this.objectMapper = objectMapper;
        this.mongoTemplate = mongoTemplate;
    }

//    public int fetchAndSave(String title, String authorFirstName, String authorLastName){
//        try {
//            String json = bookSearchService.fetchBooks(title, authorFirstName, authorLastName);
//            BookResponse bookResponse = objectMapper.readValue(json, BookResponse.class);
//            List<BookItem> items = bookResponse.getItems();
//            bookItemRepo.saveAll(items);
//            return items.size();
//        } catch (Exception e) {
//            throw new RuntimeException("Failed to fetch and save books", e);
//        }
//    }

    public int fetchAndSave(String title, String authorFirstName, String authorLastName) {
        try {
            String json = bookSearchService.fetchBooks(title, authorFirstName, authorLastName);
            BookResponse bookResponse = objectMapper.readValue(json, BookResponse.class);
            List<BookItem> items = bookResponse.getItems();

            int savedCount = 0;
            for (BookItem item : items) {
                String bookTitle = item.getVolumeInfo().getTitle();
                List<String> authors = item.getVolumeInfo().getAuthors();

                if (bookTitle != null && authors != null) {
                    Query query = new Query();
                    query.addCriteria(Criteria.where("volumeInfo.title").is(bookTitle));
                    query.addCriteria(Criteria.where("volumeInfo.authors").all(authors));

                    boolean duplicate = mongoTemplate.exists(query, BookItem.class);

                    if (!duplicate) {
                        bookItemRepo.save(item);
                        savedCount++;
                    }
                }
            }

            return savedCount;
        } catch (Exception e) {
            throw new RuntimeException("Failed to fetch and save books", e);
        }
    }




}
