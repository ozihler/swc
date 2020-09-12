package com.example.swc.news_articles.surrounding_systems;

import com.example.swc.common.HttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class NewsApi {

    private final String apiKey;
    private final String baseUrl;

    public NewsApi(
            @Value("${news.api.key}") String apiKey,
            @Value("${news.api.baseUrl}") String baseUrl) {
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    public TopHeadlinesDto getTopHeadlines(String country) throws IOException {
        String uri = String.format("%s/top-headlines?country=%s&apiKey=%s", baseUrl, country, apiKey);

        return HttpClient.invokeGet(uri, TopHeadlinesDto.class);
    }
}
