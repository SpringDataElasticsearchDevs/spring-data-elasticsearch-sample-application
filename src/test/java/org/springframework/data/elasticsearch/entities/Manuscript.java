package org.springframework.data.elasticsearch.entities;

import java.util.List;
import java.util.Set;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * Created by huse01 on 31/03/14.
 */
public class Manuscript {

	private String title;
	private String abstractText;
	@Field(type = FieldType.String, index = FieldIndex.not_analyzed)
	private String status;
	@Field(type = FieldType.Nested)
	private List<Role> role;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAbstractText() {
		return abstractText;
	}

	public void setAbstractText(String abstractText) {
		this.abstractText = abstractText;
	}

	public List<Role> getRole() {
		return role;
	}

	public void setRole(List<Role> role) {
		this.role = role;
	}
}
