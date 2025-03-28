package com.lb.book_bridge_api.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lb.book_bridge_api.dto.SaveResult;
import com.lb.book_bridge_api.model.BookItem;
import com.lb.book_bridge_api.model.BookResponse;
import com.lb.book_bridge_api.repo.BookItemRepo;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import java.util.List;
import com.lb.book_bridge_api.dto.SaveResult;

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



    public SaveResult fetchAndSave(String title, String authorFirstName, String authorLastName) {
        try {
            String json = fetchJson(title, authorFirstName, authorLastName);
            BookResponse bookResponse = parseBookResponse(json);
            List<BookItem> items = bookResponse.getItems();
            int savedCount = saveUniqueBooks(items);
            return new SaveResult(items != null ? items.size() : 0, savedCount);
        } catch (Exception e) {
            String searchContext = String.format(" [title='%s', authorFirstName='%s', authorLastName='%s']",
                    title, authorFirstName, authorLastName);
            throw new RuntimeException("Failed to fetch and save books" + searchContext, e);
        }
    }



    private String fetchJson(String title, String authorFirstName, String authorLastName) {
        return bookSearchService.fetchBooks(title, authorFirstName, authorLastName);
    }



    private BookResponse parseBookResponse(String json) throws Exception {
        return objectMapper.readValue(json, BookResponse.class);
    }



    private int saveUniqueBooks(List<BookItem> items) {
        int savedCount = 0;
        for (BookItem item : items) {
            if (isUnique(item)) {
                bookItemRepo.save(item);
                savedCount++;
            }
        }
        return savedCount;
    }



    private boolean isUnique(BookItem item) {
        String bookTitle = item.getVolumeInfo().getTitle();
        List<String> authors = item.getVolumeInfo().getAuthors();

        if (bookTitle == null || authors == null) {
            return false;
        }

        Query query = new Query();
        query.addCriteria(Criteria.where("volumeInfo.title").is(bookTitle));
        query.addCriteria(Criteria.where("volumeInfo.authors").all(authors));

        return !mongoTemplate.exists(query, BookItem.class);
    }



}
