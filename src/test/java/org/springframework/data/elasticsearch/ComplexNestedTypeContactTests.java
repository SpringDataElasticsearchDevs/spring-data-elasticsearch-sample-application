package org.springframework.data.elasticsearch;

import static org.elasticsearch.index.query.QueryBuilders.*;
import static org.hamcrest.core.Is.is;

import java.util.Arrays;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.data.elasticsearch.entities.Contact;
import org.springframework.data.elasticsearch.entities.Manuscript;
import org.springframework.data.elasticsearch.entities.Role;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by huse01 on 31/03/14.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/contact-test.xml")
public class ComplexNestedTypeContactTests {

	@Autowired
	private ElasticsearchTemplate template;

	@Before
	public void before() {
		template.deleteIndex(Contact.class);
		template.createIndex(Contact.class);
		template.putMapping(Contact.class);
	}

	@Test
	public void test() {

		Contact contact1 = new Contact();
		contact1.setId("1");
		contact1.setName("2");

		Manuscript manuscript1 = new Manuscript();
		manuscript1.setTitle("t1");
		manuscript1.setAbstractText("t2");
		manuscript1.setStatus("ACCEPTED");

		Role role1 = new Role();
		role1.setName("role1");

		Role role2 = new Role();
		role2.setName("role2");

		manuscript1.setRole(Arrays.asList(role1, role2));

		Manuscript manuscript2 = new Manuscript();
		manuscript2.setTitle("t1");
		manuscript2.setAbstractText("t2");
		manuscript2.setStatus("DELETED");

		Role role3 = new Role();
		role3.setName("role3");

		manuscript2.setRole(Arrays.asList(role3));

		contact1.setManuscripts(Arrays.asList(manuscript1,manuscript2));

		IndexQuery indexQuery = new IndexQueryBuilder().withObject(contact1)
				.withIndexName("test-contact-test").withId(contact1.getId())
				.withType("contact-test-type").build();

		template.bulkIndex(Arrays.asList(indexQuery));
		template.refresh("test-contact-test", true);

		BoolQueryBuilder builder = boolQuery();
		builder.must(nestedQuery("manuscripts", termQuery("manuscripts.status", "ACCEPTED")))
				.must(nestedQuery("manuscripts.role", termQuery("manuscripts.role.name", "role3")));

		SearchQuery searchQuery = new NativeSearchQueryBuilder()
				.withQuery(builder).build();

		Page<Contact> page  = template.queryForPage(searchQuery, Contact.class);
		Assert.assertThat(page.getTotalElements(), is(1L));

	}

}
