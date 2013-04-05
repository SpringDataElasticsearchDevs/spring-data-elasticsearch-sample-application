package org.springframework.data.elasticsearch.repositories;

import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.data.elasticsearch.entities.Product;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import static org.elasticsearch.index.query.QueryBuilders.fieldQuery;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/springContext-test.xml")
public class SampleProductRepositoryTest {

    @Resource
    private SampleProductRepository repository;

    @Before
    public void emptyData(){
        repository.deleteAll();
    }

    @Test
    public void shouldReturnSingleProductByName() {
        //given
        repository.index(new Product("1", "testProduct1",
                "How great would it be if we could search for this product.",
                true));
        repository
                .index(new Product(
                        "2",
                        "testProduct2",
                        "How great would it be if we could search for this other product.",
                        true));
        //when
        Product product = repository.findByName("testProduct2");
        //then
        assertNotNull(product);
    }

    @Test
    public void shouldReturnPageOfBookByName(){
        //given
        repository.index(new Product("1", "test product 1",
                "How great would it be if we could search for this product.",
                true));
        repository
                .index(new Product(
                        "2",
                        "test product 2",
                        "How great would it be if we could search for this other product.",
                        true));
        //when
        Page<Product> product = repository.findByName("product", new PageRequest(0,2));
        //then
        assertThat(product.getContent().size(), is(2));
    }

    @Test
    public void shouldReturnPageOfBookUsingVariousSearchCriteria(){
        //given
        repository.save(new Product("1", "test product 1",
                "How great would it be if we could search for this product.",
                true));
        repository
                .save(new Product(
                        "2",
                        "test product 2",
                        "How great would it be if we could search for this other product.",
                        true));
        //when
        Page<Product> productsUsingQueryBuilder = repository.search(fieldQuery("name", "product"),new PageRequest(0,10));

        SearchQuery searchQuery = new SearchQuery();
        searchQuery.setElasticsearchQuery(fieldQuery("name", "product"));
        searchQuery.setPageable(new PageRequest(0,10));
        Page<Product> productsUsingSearchQuery = repository.search(searchQuery);
        //then
        assertThat(productsUsingQueryBuilder.getContent().size(), is(2));

        assertThat(productsUsingSearchQuery.getContent().size(),is(2));
    }


}
