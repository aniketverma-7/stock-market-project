package com.project.stock.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.stock.dto.GenericResponse;
import com.project.stock.dto.stocks.chart.ChartRequest;
import com.project.stock.dto.stocks.chart.ChartResponse;
import com.project.stock.exception.GlobalExceptionHandler;
import com.project.stock.service.ChartService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class ChartServiceImpl implements ChartService {


    @Value("${chart.api.url}")
    private String url;

    @Value("${chart.api.key}")
    private String apiKey;

    @Value("${chart.api.host}")
    private String apiHost;

    @Override
    public Object getChart(String symbol) throws IOException {

        OkHttpClient client = new OkHttpClient();

        System.out.println(url + symbol);

        Request request = new Request.Builder()
                .url(url + symbol)
                .get()
                .addHeader("X-RapidAPI-Key", apiKey)
                .addHeader("X-RapidAPI-Host", apiHost)
                .build();
        Response response = client.newCall(request).execute();

        if (response.isSuccessful()) {
            return new ObjectMapper().readTree(response.body().string());
        } else {
            return new GenericResponse(HttpStatus.BAD_REQUEST.value(), response.message());
        }
    }
}
