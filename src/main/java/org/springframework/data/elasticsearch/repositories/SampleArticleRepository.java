package org.springframework.data.elasticsearch.repositories;

import org.springframework.data.elasticsearch.entities.Article;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface SampleArticleRepository extends ElasticsearchRepository<Article,String> {
}
