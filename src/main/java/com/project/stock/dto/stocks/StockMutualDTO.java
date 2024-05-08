package com.project.stock.dto.stocks;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.ArrayList;

@Data
public class StockMutualDTO {

    public String status;
    public String request_id;
    public SearchData data;
}





