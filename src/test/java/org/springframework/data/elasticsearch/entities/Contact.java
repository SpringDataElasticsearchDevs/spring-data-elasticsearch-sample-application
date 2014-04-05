package org.springframework.data.elasticsearch.entities;

import java.util.List;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Created by huse01 on 31/03/14.
 */
@Document(indexName = "test-contact-test",type = "contact-test-type", shards = 1, replicas = 0)
public class Contact {

	@Id
	private String id;
	private String name;
	@Field(type = FieldType.Nested)
	private List<Manuscript> manuscripts;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Manuscript> getManuscripts() {
		return manuscripts;
	}

	public void setManuscripts(List<Manuscript> manuscripts) {
		this.manuscripts = manuscripts;
	}
}
