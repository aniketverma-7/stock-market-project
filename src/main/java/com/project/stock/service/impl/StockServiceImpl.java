package com.project.stock.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.stock.dto.stocks.SearchRequest;
import com.project.stock.exception.GlobalExceptionHandler;
import com.project.stock.service.StockService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockServiceImpl implements StockService {


    @Value("${search.api.url}")
    private String url;

    @Value("${search.api.key}")
    private String apiKey;

    @Value("${search.api.host}")
    private String apiHost;


    @Override
    public List<?> searchStockByName(String name) throws GlobalExceptionHandler {
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
                SearchRequest stockMutualDTO = mapper.readValue(responseBody, SearchRequest.class);

                List<Object> searchResult = new ArrayList<>();
                stockMutualDTO.getData().getStock().forEach(searchResult::add);
                return searchResult;
            }
        } catch (IOException e) {
            throw new GlobalExceptionHandler();
        }
        return null;
    }

    @Override
    public Double getCurrentPrice(String name) throws GlobalExceptionHandler {
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
                SearchRequest stockMutualDTO = mapper.readValue(responseBody, SearchRequest.class);
                return stockMutualDTO.getData().getStock().getFirst().getPrice();
            }
        } catch (IOException e) {
            throw new GlobalExceptionHandler();
        }
        return null;
    }
}
