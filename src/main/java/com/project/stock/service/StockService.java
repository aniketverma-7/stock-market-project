package com.project.stock.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.stock.dto.news.NewsDTO;
import com.project.stock.dto.stocks.MutualFundDTO;
import com.project.stock.dto.stocks.StockDTO;
import com.project.stock.dto.stocks.StockMutualDTO;
import com.project.stock.exception.GlobalExceptionHandler;
import com.project.stock.model.stocks.Stocks;
import org.springframework.beans.factory.annotation.Value;
import okhttp3.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;

@Service
public class StockService {

    @Value("${search.api.url}")
    private String url;

    @Value("${search.api.key}")
    private String apiKey;

    @Value("${search.api.host}")
    private String apiHost;


    public StockMutualDTO searchStockByName(String name) throws GlobalExceptionHandler {
        String apiUrl = url + name;
        System.out.println(apiUrl);

        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(apiUrl)
                .addHeader("X-RapidAPI-Key", apiKey)
                .addHeader("X-RapidAPI-Host", apiHost)
                .build();

        try {
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                assert response.body() != null;
                String responseBody = response.body().string();
                ObjectMapper mapper = new ObjectMapper();
                return mapper.readValue(responseBody, StockMutualDTO.class);
            }
        } catch (IOException e) {
            throw new GlobalExceptionHandler();
        }
        return null;
    }
}
