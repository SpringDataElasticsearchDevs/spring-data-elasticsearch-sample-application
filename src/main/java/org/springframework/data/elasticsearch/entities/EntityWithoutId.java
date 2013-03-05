package org.springframework.data.elasticsearch.entities;


import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "book",type = "book")
public class EntityWithoutId {

    private String name;
    private Long price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
