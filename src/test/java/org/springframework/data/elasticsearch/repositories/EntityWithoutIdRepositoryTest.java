package org.springframework.data.elasticsearch.repositories;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.ResultsMapper;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.core.query.StringQuery;
import org.springframework.data.elasticsearch.entities.EntityWithoutId;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/springContext-test.xml")
public class EntityWithoutIdRepositoryTest {

    @Resource
    private EntityWithoutIdRepository repository;

    @Resource
    private ElasticsearchTemplate template;

    @Before
    public void emptyData(){
        repository.deleteAll();
    }

    @Test
    public void shouldIndexEntityWithNoId(){

        EntityWithoutId entity = new EntityWithoutId();

        entity.setName("test");
        entity.setPrice(12L);

        repository.save(entity);

        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setElasticsearchQuery(matchAllQuery());
        searchQuery.addIndices("book");
        searchQuery.addTypes("book");
        List<String> ids =  template.queryForIds(searchQuery);

        for(String id : ids){
            System.out.println("Ids : " + id);
        }

        StringQuery stringQuery = new StringQuery(fieldQuery("name",entity.getName()).toString());
        EntityWithoutId entityWithoutId = template.queryForObject(stringQuery, EntityWithoutId.class);

        assertThat(entityWithoutId, is(notNullValue()));
     }



}
