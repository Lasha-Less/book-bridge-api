package com.lb.book_bridge_api.repo;

import com.lb.book_bridge_api.model.BookItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BookItemRepo extends MongoRepository<BookItem, String> {

    List<BookItem> findByVolumeInfoTitleAndVolumeInfoAuthors(String title, List<String> authors);

    @Query("{ 'volumeInfo.title': ?0 }")
    List<BookItem> findByTitleCustom(String title);


}
