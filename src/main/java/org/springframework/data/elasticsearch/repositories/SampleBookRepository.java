package org.springframework.data.elasticsearch.repositories;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.entities.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface SampleBookRepository extends ElasticsearchRepository<Book,String> {
    Page<Book> findByNameAndPrice(String name, Integer price, Pageable pageable);
}
