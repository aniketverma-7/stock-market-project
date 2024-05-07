package com.project.stock.service;

import com.project.stock.dto.news.NewsDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NewsAPIService {

    @Value("${news.api.url}")
    private String newsApiUrl;

    @Value("${news.api.key}")
    private String newsApiKey;

    public NewsDTO getNews(String ticker) {
        String apiUrl = constructURL(ticker);
        System.out.println("Log Endpoint: " + apiUrl);
        ResponseEntity<NewsDTO> response = new RestTemplate().getForEntity(apiUrl, NewsDTO.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            return response.getBody();
        } else {
            return null;
        }
    }

    private String constructURL(String ticker) {
        return newsApiUrl + ((ticker.isEmpty()) ? "" : "&tickers=" + ticker) + "&apikey=" + newsApiKey;
    }

}
