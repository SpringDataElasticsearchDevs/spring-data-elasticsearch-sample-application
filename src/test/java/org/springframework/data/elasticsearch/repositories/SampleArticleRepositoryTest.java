package org.springframework.data.elasticsearch.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.elasticsearch.entities.Article;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/springContext-test.xml")
public class SampleArticleRepositoryTest {

    @Resource
    private SampleArticleRepository sampleArticleRepository;

    @Before
    public void emptyData(){
        sampleArticleRepository.deleteAll();
    }

    @Test
    public void shouldIndexSingleBookEntity(){

        Article article = new Article();
        article.setId("123455");
        article.setTitle("Spring Data Elasticsearch");
        List<String> authors = new ArrayList<String>();
        authors.add("Author1");
        authors.add("Author2");
        article.setAuthors(authors);
        List<String> tags = new ArrayList<String>();
        tags.add("tag1");
        tags.add("tag2");
        tags.add("tag3");
        article.setTags(tags);
        //Indexing using sampleArticleRepository
        sampleArticleRepository.save(article);
        //lets try to search same record in elasticsearch
        Article indexedArticle = sampleArticleRepository.findOne(article.getId());
        assertThat(indexedArticle,is(notNullValue()));
        assertThat(indexedArticle.getId(),is(article.getId()));
        assertThat(indexedArticle.getAuthors().size(),is(authors.size()));
        assertThat(indexedArticle.getTags().size(),is(tags.size()));
    }
}
