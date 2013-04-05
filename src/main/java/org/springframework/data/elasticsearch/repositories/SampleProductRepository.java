package org.springframework.data.elasticsearch.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.entities.Product;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SampleProductRepository extends ElasticsearchRepository<Product,String> {
    Product findByName(String name);
    Page<Product> findByName(String name, Pageable pageable);
}
