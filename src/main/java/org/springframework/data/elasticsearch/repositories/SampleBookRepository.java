package org.springframework.data.elasticsearch.repositories;


import org.springframework.data.elasticsearch.entities.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SampleBookRepository extends ElasticsearchRepository<Book,String> {

}
