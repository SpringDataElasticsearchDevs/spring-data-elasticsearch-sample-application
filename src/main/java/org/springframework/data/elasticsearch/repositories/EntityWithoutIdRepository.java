package org.springframework.data.elasticsearch.repositories;

import org.springframework.data.elasticsearch.entities.EntityWithoutId;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface EntityWithoutIdRepository extends ElasticsearchRepository<EntityWithoutId,String> {
}
