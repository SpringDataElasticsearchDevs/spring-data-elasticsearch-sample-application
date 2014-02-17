package org.springframework.data.elasticsearch.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.MultiField;
import org.springframework.data.elasticsearch.annotations.NestedField;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.springframework.data.elasticsearch.annotations.FieldIndex.analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldIndex.not_analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldType.Integer;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;

@Document(indexName = "articles", type = "article", shards = 1, replicas = 0, refreshInterval = "-1", indexStoreType = "memory")
public class Article {

    @Id
    private String id;

    private String title;

    @MultiField(
            mainField = @Field(type = String, index = analyzed),
            otherFields = {
                    @NestedField(dotSuffix = "untouched", type = String, store = true, index = not_analyzed),
                    @NestedField(dotSuffix = "sort", type = String, store = true, indexAnalyzer = "keyword")
            }
    )
    private List<String> authors = new ArrayList<String>();

    @Field(type = Integer, store = true)
    private List<Integer> publishedYears = new ArrayList<Integer>();

    @Field(type = String, store = true)
    private Collection<String> tags = new ArrayList<String>();

    private int score;

    public Article() {

    }

    public Article(String id) {
        this.id = id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    public List<Integer> getPublishedYears() {
        return publishedYears;
    }

    public void setPublishedYears(List<Integer> publishedYears) {
        this.publishedYears = publishedYears;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Collection<String> getTags() {
        return tags;
    }

    public void setTags(Collection<String> tags) {
        this.tags = tags;
    }
}
