package com.lb.book_bridge_api.repo;

import com.lb.book_bridge_api.model.BookItem;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BookItemRepo extends MongoRepository<BookItem, String> {


}
