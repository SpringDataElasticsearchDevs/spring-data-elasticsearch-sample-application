package org.springframework.data.elasticsearch.repositories;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.entities.OperationDocument;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-context.xml")
public class OperationDocumentRepositoryTest {

	@Autowired
	private ElasticsearchTemplate elasticsearchTemplate;

	@Autowired
	private OperationDocumentRepository repository;

	@Test
	public void test() {
		Map mapping = elasticsearchTemplate.getMapping(OperationDocument.class);
		System.out.println(mapping.toString());
	}


}