/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.data.elasticsearch.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.entities.Author;
import org.springframework.data.elasticsearch.entities.Book;
import org.springframework.data.elasticsearch.entities.Person;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.apache.commons.lang.RandomStringUtils.randomAlphanumeric;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Rizwan Idrees
 * @author Mohsin Husen
 * @author Artur Konczak
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/springContext-test.xml")
public class NestedObjectTests {

	@Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    private SampleBookRepository bookRepository;

    @Before
    public void before() {
        elasticsearchTemplate.deleteIndex(Book.class);
        elasticsearchTemplate.createIndex(Book.class);
        elasticsearchTemplate.refresh(Book.class, true);
        elasticsearchTemplate.deleteIndex(Person.class);
        elasticsearchTemplate.createIndex(Person.class);
        elasticsearchTemplate.putMapping(Person.class);
        elasticsearchTemplate.refresh(Person.class, true);
    }

    @Test
    public void shouldIndexInnerObject() {
        // given
        String id = randomAlphanumeric(5);
        Book book = new Book();
        book.setId(id);
        book.setName("xyz");
        Author author = new Author();
        author.setId("1");
        author.setName("ABC");
        // when
        bookRepository.save(book);
        // then
        assertThat(bookRepository.findOne(id), is(notNullValue()));
    }
}
