package com.example.swc.news_articles.adapters.presentation;

import com.example.swc.news_articles.surrounding_systems.NewsApi;
import com.example.swc.news_articles.surrounding_systems.NewsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class NewsResource {

    private NewsApi newsApi;

    @Autowired
    public NewsResource(NewsApi newsApi) {
        this.newsApi = newsApi;
    }

    @GetMapping("/api/news/top-headlines")
    public ResponseEntity<NewsDto> getTopHeadlines(
            @RequestParam("country") String country) throws IOException {
        return ResponseEntity.ok(
                this.newsApi.getTopHeadlines(country)
        );
    }
}
