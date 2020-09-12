package com.example.swc.news_articles.surrounding_systems;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
class SourceDto {
    public String id;
    public String name;
}

@JsonIgnoreProperties(ignoreUnknown = true)
class ArticlesDto {
    public SourceDto source;
    public String author;
    public String title;
    public String description;
    public String url;
    public String urlToImage;
    public String publishedAt;
    public String content;
}

@JsonIgnoreProperties(ignoreUnknown = true)
public class TopHeadlinesDto {
    public String status;
    public int totalResults;
    public ArticlesDto[] articles;
}
