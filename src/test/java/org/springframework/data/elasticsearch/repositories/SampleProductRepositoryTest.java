package org.springframework.data.elasticsearch.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.entities.Product;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/springContext-test.xml")
public class SampleProductRepositoryTest {

    @Resource
    private SampleProductRepository sampleProductRepository;

    @Before
    public void emptyData(){
        sampleProductRepository.deleteAll();
    }

    @Test
    public void shouldReturnListOfProductsByName() {
        //given
        sampleProductRepository.index(new Product("1", "test product 1",
                "How great would it be if we could search for this product.",
                true));
        sampleProductRepository
                .index(new Product(
                        "2",
                        "test Product 2",
                        "How great would it be if we could search for this other product.",
                        true));
        //when
        List<Product> products = sampleProductRepository.findByName("product");
        //then
        assertThat(products.size(), is(2));
    }

    @Test
    public void shouldReturnListOfBookByNameWithPageable(){
        //given
        sampleProductRepository.index(new Product("1", "test product 1",
                "How great would it be if we could search for this product.",
                true));
        sampleProductRepository
                .index(new Product(
                        "2",
                        "test product 2",
                        "How great would it be if we could search for this other product.",
                        true));
        //when
        List<Product> products = sampleProductRepository.findByName("product", new PageRequest(0,1));
        //then
        assertThat(products.size(), is(1));
    }

    @Test
    public void shouldReturnListOfProductsForGivenNameAndId(){
        //given
        sampleProductRepository.save(new Product("1", "test product 1",
                "How great would it be if we could search for this product.",
                true));
        sampleProductRepository
                .save(new Product(
                        "2",
                        "test product 2",
                        "How great would it be if we could search for this other product.",
                        true));
        List<Product> products = sampleProductRepository.findByNameAndId("product","1");

        //then
        assertThat(products.size(),is(1));
    }
}
