package org.springframework.data.elasticsearch.repositories;

import org.springframework.data.elasticsearch.entities.OperationDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * Created by mohsinhusen on 10/04/15.
 */
public interface OperationDocumentRepository  extends ElasticsearchRepository<OperationDocument, Long> {

}
