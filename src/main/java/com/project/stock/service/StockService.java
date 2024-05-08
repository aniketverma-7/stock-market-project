package com.project.stock.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.stock.dto.stocks.StockMutualDTO;
import com.project.stock.exception.GlobalExceptionHandler;
import org.springframework.beans.factory.annotation.Value;
import okhttp3.*;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StockService {

    @Value("${search.api.url}")
    private String url;

    @Value("${search.api.key}")
    private String apiKey;

    @Value("${search.api.host}")
    private String apiHost;


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
                StockMutualDTO stockMutualDTO = mapper.readValue(responseBody, StockMutualDTO.class);

                List<Object> searchResult = new ArrayList<>();
                stockMutualDTO.getData().getStock().forEach(searchResult::add);
                stockMutualDTO.getData().getMutualFunds().forEach(searchResult::add);

                return searchResult;
            }
        } catch (IOException e) {
            throw new GlobalExceptionHandler();
        }
        return null;
    }
}
